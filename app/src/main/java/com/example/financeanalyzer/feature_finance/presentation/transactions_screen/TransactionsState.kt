package com.example.financeanalyzer.feature_finance.presentation.transactions_screen

import com.example.financeanalyzer.feature_finance.domain.model.CategoryGroupItem

data class TransactionsState(
    val categoryGroupItems: List<CategoryGroupItem> = emptyList(),
    val normalValue: Float = 0f,
    val constantValue: Float = 0f,
    val currentMonth: String = "",
    val transactionType: Int = 1,
    val isLoading: Boolean = true
)
