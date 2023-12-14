package com.example.financeanalyzer.feature_finance.presentation.util

sealed class Screen(val route: String) {

    object MainScreen: Screen("main_screen")
    object ExpenseScreen: Screen("expense_screen")
    object IncomeScreen: Screen("income_screen")
}
