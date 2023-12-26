package com.example.financeanalyzer.feature_finance.presentation.expense_screen

import com.example.financeanalyzer.feature_finance.domain.model.CategoryGroupItem

data class ExpenseState(
    val categoryGroupItems: List<CategoryGroupItem> = emptyList(),
    val normalExpense: Float = 0f,
    val constantExpense: Float = 0f,
    val isLoading: Boolean = true
)
