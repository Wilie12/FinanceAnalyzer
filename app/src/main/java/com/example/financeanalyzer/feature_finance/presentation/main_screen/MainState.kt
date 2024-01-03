package com.example.financeanalyzer.feature_finance.presentation.main_screen

import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import com.example.financeanalyzer.feature_finance.domain.model.Transaction

data class MainState(
    val transactions: List<Transaction> = emptyList(),
    val constantTransactions: List<ConstantTransaction> = emptyList(),
    val income: Float = 0f,
    val expense: Float = 0f,
    val currentMonth: String = "",
    val isLoading: Boolean = true
)
