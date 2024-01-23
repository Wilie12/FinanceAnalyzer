package com.example.financeanalyzer.feature_finance.presentation.constant_transactions_screen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import com.example.financeanalyzer.feature_finance.presentation.common.FinanceTopBar
import com.example.financeanalyzer.feature_finance.presentation.constant_transactions_screen.components.ConstantTransactionItem
import com.example.financeanalyzer.feature_finance.presentation.util.Screen
import java.math.RoundingMode

@Composable
fun ConstantTransactionsScreen(
    navController: NavController,
    viewModel: ConstantTransactionsViewModel = hiltViewModel()
) {
    var animationPlayed by remember { mutableStateOf(false) }
    val animatedValue by animateFloatAsState(    
        targetValue = if (animationPlayed) {
            if (viewModel.state.value.transactionType == ConstantTransaction.TYPE_EXPENSE) {
                viewModel.state.value.totalConstantExpense
            } else {
                viewModel.state.value.totalConstantIncome
            }
        } else 0f,
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearEasing
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            FinanceTopBar(
                navController = navController,
                title = "Stałe ${if (viewModel.state.value.transactionType == ConstantTransaction.TYPE_EXPENSE) "wydatki" else "przychody"}"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFF87CEEB),
                        shape = RoundedCornerShape(32.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = viewModel.state.value.currentMonth,
                    fontSize = 20.sp,
                    color = Color.White
                )
                Text(
                    text = "-${
                        animatedValue.toBigDecimal().setScale(2, RoundingMode.HALF_DOWN)
                    }zł",
                    fontSize = 40.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Wszystkie",
                color = Color.Black,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viewModel.state.value.constantTransactions) {
                    ConstantTransactionItem(
                        constantTransaction = it,
                    ) { id ->
                        navController.navigate(Screen.ConstantTransactionEditScreen.route + "/$id")
                    }
                }
            }
        }
        Text(
            text = "Dodaj ${if (viewModel.state.value.transactionType == ConstantTransaction.TYPE_EXPENSE) "wydatek" else "przychód"}",
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    color = Color(0xFF6082B6),
                    shape = RoundedCornerShape(32.dp)
                )
                .clip(RoundedCornerShape(32.dp))
                .clickable { navController.navigate(Screen.AddTransactionScreen.route) }
                .padding(8.dp)
        )
    }
}