package com.example.financeanalyzer.feature_finance.presentation.constant_transactions_screen

import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction

data class ConstantTransactionsState(
    val constantTransactions: List<ConstantTransaction> = emptyList(),
    val totalConstantExpense: Float = 0f,
    val totalConstantIncome: Float = 0f,
    val currentMonth: String = "",
    val transactionType: Int = 0
)