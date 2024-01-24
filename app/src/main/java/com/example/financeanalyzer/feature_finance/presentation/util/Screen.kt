package com.example.financeanalyzer.feature_finance.presentation.util

sealed class Screen(val route: String) {

    object MainScreen: Screen("main_screen")
    object TransactionsScreen: Screen("transactions_screen")
    object IncomeScreen: Screen("income_screen")
    object AddTransactionScreen: Screen("add_transaction_screen")
    object NormalCategoryScreen: Screen("normal_category_screen")
    object ConstantTransactionsScreen: Screen("constant_transactions_screen")
    object ConstantTransactionEditScreen: Screen("constant_transaction_edit_screen")
}
