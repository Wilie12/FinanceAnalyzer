package com.example.financeanalyzer.feature_finance.presentation.main_screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeanalyzer.R
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.presentation.common.FinanceTopBar
import com.example.financeanalyzer.feature_finance.presentation.main_screen.components.FinanceArc
import com.example.financeanalyzer.feature_finance.presentation.main_screen.components.TextWithValue
import com.example.financeanalyzer.feature_finance.presentation.main_screen.components.TransactionItem
import com.example.financeanalyzer.feature_finance.presentation.util.Screen

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    var animationPlayed by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                color = Color(0xFF6082B6),
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Column(modifier = Modifier.align(Alignment.TopCenter)) {
                FinanceTopBar(
                    navController = navController,
                    title = "Finance Analyzer",
                    isBackVisible = false
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFF87CEEB),
                            shape = RoundedCornerShape(32.dp)
                        )
                        .padding(16.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = viewModel.state.value.currentMonth,
                            fontSize = 20.sp,
                            color = Color.White,
                            textAlign = TextAlign.Start
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    FinanceArc(
                        maxValue = state.income,
                        value = state.income - state.expense
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        color = Color.White,
                        thickness = 2.dp,
                        modifier = Modifier.clip(RoundedCornerShape(16.dp))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextWithValue(
                            text = "Wydatki",
                            value = state.expense,
                            isPositive = false
                        )
                        TextWithValue(
                            text = "Przychody",
                            value = state.income,
                            isPositive = true
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ostatnie",
                    color = Color.Black,
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (state.transactions.isEmpty()) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.8f)
                    ) {
                        Text(
                            text = "Brak danych",
                            color = Color.Gray,
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp
                        )
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .animateContentSize(
                                animationSpec = tween(
                                    durationMillis = 500,
                                    easing = LinearEasing
                                )
                            )
                            .fillMaxHeight(if (animationPlayed) 1f else 0f)
                    ) {
                        items(state.transactions) { transaction ->
                            TransactionItem(transaction = transaction)
                        }
                        item {
                            Spacer(modifier = Modifier.height(60.dp))
                        }
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFF6082B6),
                        shape = RoundedCornerShape(32.dp)
                    )
                    .align(Alignment.BottomCenter)
                    .padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_income),
                    contentDescription = "Expenses",
                    tint = Color.White,
                    modifier = Modifier
                        .size(40.dp)
                        .rotate(180f)
                        .weight(4f)
                        .clip(RoundedCornerShape(32.dp))
                        .clickable {
                            navController.navigate(Screen.TransactionsScreen.route + "/${Transaction.TYPE_INCOME}")
                        }
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_analize),
                    contentDescription = "Analyze",
                    tint = Color(0xFF6082B6),
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(32.dp)
                        )
                        .weight(2f)
                        .clip(RoundedCornerShape(32.dp))
                        .clickable {
                            navController.navigate(Screen.AnalysisScreen.route)
                        }
                        .padding(8.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_income),
                    contentDescription = "Incomes",
                    tint = Color.White,
                    modifier = Modifier
                        .size(40.dp)
                        .weight(4f)
                        .clip(RoundedCornerShape(32.dp))
                        .clickable {
                            navController.navigate(Screen.TransactionsScreen.route + "/${Transaction.TYPE_EXPENSE}")
                        }
                )
            }
        }
    }
    LaunchedEffect(key1 = viewModel.state.value.isLoading) {
        if (!viewModel.state.value.isLoading) {
            animationPlayed = true
        }
    }
}