package com.example.financeanalyzer.feature_finance.presentation.normal_category_screen

import com.example.financeanalyzer.feature_finance.data.util.Constants
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.model.TransactionCategory

data class NormalCategoryState(
    val category: TransactionCategory = Constants.transactionCategories[0],
    val transactionsExpense: List<Transaction> = emptyList(),
    val totalExpenseOnCategory: Float = 0f,
    val currentMonth: String = "",
    val isLoading: Boolean = true
)