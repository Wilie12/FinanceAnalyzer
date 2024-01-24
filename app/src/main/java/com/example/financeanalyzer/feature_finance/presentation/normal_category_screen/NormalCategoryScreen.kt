package com.example.financeanalyzer.feature_finance.presentation.normal_category_screen

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
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.presentation.common.FinanceTopBar
import com.example.financeanalyzer.feature_finance.presentation.main_screen.components.TransactionItem
import com.example.financeanalyzer.feature_finance.presentation.util.Screen
import java.math.RoundingMode

@Composable
fun NormalCategoryScreen(
    navController: NavController,
    viewModel: NormalCategoryViewModel = hiltViewModel()
) {

    var animationPlayed by remember { mutableStateOf(false) }
    val animatedTotalExpenseOnCategory by animateFloatAsState(
        targetValue = if (animationPlayed) { viewModel.state.value.totalValueOnCategory } else 0f,
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
                title = viewModel.state.value.category.name
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = viewModel.state.value.category.color,
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
                    text = "${if (viewModel.state.value.category.transactionType == Transaction.TYPE_EXPENSE) "-" else ""}${animatedTotalExpenseOnCategory.toBigDecimal().setScale(2, RoundingMode.HALF_DOWN)}zł",
                    fontSize = 40.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Ostatnie",
                color = Color.Black,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxHeight()
            ) {
                items(viewModel.state.value.transactions) { transaction ->
                    TransactionItem(
                        transaction = transaction,
                        isDetailed = true
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(60.dp))
                }
            }
        }
        Text(
            text = "Dodaj wydatek",
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