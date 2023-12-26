package com.example.financeanalyzer.feature_finance.domain.use_case

data class ExpenseUseCases(
    val getAllTransactionsGroupedByCategoryFromCurrentMonth: GetAllTransactionsGroupedByCategoryFromCurrentMonth,
    val getConstantTransactions: GetConstantTransactions
)
