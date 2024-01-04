package com.example.financeanalyzer.feature_finance.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.financeanalyzer.feature_finance.presentation.add_transaction_screen.AddTransactionScreen
import com.example.financeanalyzer.feature_finance.presentation.constant_transactions_screen.ConstantTransactionsScreen
import com.example.financeanalyzer.feature_finance.presentation.expense_screen.ExpenseScreen
import com.example.financeanalyzer.feature_finance.presentation.income_screen.IncomeScreen
import com.example.financeanalyzer.feature_finance.presentation.main_screen.MainScreen
import com.example.financeanalyzer.feature_finance.presentation.normal_category_screen.NormalCategoryScreen
import com.example.financeanalyzer.feature_finance.presentation.util.Screen
import com.example.financeanalyzer.ui.theme.FinanceAnalyzerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.statusBarColor = Color(0xFF6082B6).toArgb()
            FinanceAnalyzerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MainScreen.route
                    ) {
                        composable(Screen.MainScreen.route) {
                            MainScreen(navController = navController)
                        }
                        composable(Screen.ExpenseScreen.route) {
                            ExpenseScreen(navController = navController)
                        }
                        composable(Screen.IncomeScreen.route) {
                            IncomeScreen(navController = navController)
                        }
                        composable(Screen.AddTransactionScreen.route) {
                            AddTransactionScreen(navController = navController)
                        }
                        composable(
                            route = Screen.NormalCategoryScreen.route + "/{categoryId}",
                            arguments = listOf(
                                navArgument("categoryId") {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            NormalCategoryScreen(navController = navController)
                        }
                        composable(
                            route = Screen.ConstantTransactionsScreen.route + "/{transactionType}",
                            arguments = listOf(
                                navArgument("transactionType") {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            ConstantTransactionsScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}