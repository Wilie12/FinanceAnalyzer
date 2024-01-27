package com.example.financeanalyzer.feature_finance.presentation.analysis_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeanalyzer.feature_finance.presentation.analysis_screen.components.FinanceBar
import com.example.financeanalyzer.feature_finance.presentation.common.FinanceTopBar
import com.example.financeanalyzer.feature_finance.presentation.transactions_screen.components.CategoryItem
import com.example.financeanalyzer.feature_finance.presentation.util.Screen

@Composable
fun AnalysisScreen(
    navController: NavController,
    viewModel: AnalysisViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        if (viewModel.state.value.isLoading) {
            CircularProgressIndicator(
                color = Color(0xFF6082B6),
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Column(modifier = Modifier.align(Alignment.TopCenter)) {
                FinanceTopBar(navController = navController, title = "Analiza")
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
                        text = "Poprzedni miesiąc",
                        fontSize = 20.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )
                    if (viewModel.state.value.previousMonthTransactions.isEmpty()) {
                        Text(
                            text = "Brak danych",
                            fontSize = 18.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        FinanceBar(
                            maxValue = viewModel.state.value.previousMonthIncome,
                            value = viewModel.state.value.previousMonthIncome - viewModel.state.value.previousMonthExpense,
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Obecny miesiąc",
                        fontSize = 20.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )
                    if (viewModel.state.value.currentMonthTransactions.isEmpty()) {
                        Text(
                            text = "Brak danych",
                            fontSize = 18.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        FinanceBar(
                            maxValue = viewModel.state.value.currentMonthIncome,
                            value = viewModel.state.value.currentMonthIncome - viewModel.state.value.currentMonthExpense,
                        )
                    }
                }
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Największy przychód",
                        color = Color.Black,
                        fontSize = 22.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    if (viewModel.state.value.currentMonthIncome <= 0f) {
                        Text(
                            text = "Brak danych",
                            fontSize = 18.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        CategoryItem(
                            categoryGroupItem = viewModel.state.value.currentMonthMostIncomeCategory,
                            onClick = {
                                navController.navigate(Screen.NormalCategoryScreen.route + "/$it")
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Największy wydatek",
                        color = Color.Black,
                        fontSize = 22.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    if (viewModel.state.value.currentMonthExpense <= 0f) {
                        Text(
                            text = "Brak danych",
                            fontSize = 18.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        CategoryItem(
                            categoryGroupItem = viewModel.state.value.currentMonthMostExpenseCategory,
                            onClick = {
                                navController.navigate(Screen.NormalCategoryScreen.route + "/$it")
                            }
                        )
                    }
                }
            }
        }
        Text(
            text = "Wydatki i przychody stałe nie są uwzględniane w analizie",
            color = Color.Gray,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}