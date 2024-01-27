package com.example.financeanalyzer.feature_finance.presentation.analysis_screen

import com.example.financeanalyzer.feature_finance.data.util.Constants
import com.example.financeanalyzer.feature_finance.domain.model.CategoryGroupItem
import com.example.financeanalyzer.feature_finance.domain.model.Transaction

data class AnalysisState(
    val isLoading: Boolean = true,
    val currentMonthExpense: Float = 0f,
    val currentMonthIncome: Float = 0f,
    val currentMonthTransactions: List<Transaction> = emptyList(),
    val previousMonthExpense: Float = 0f,
    val previousMonthIncome: Float = 0f,
    val previousMonthTransactions: List<Transaction> = emptyList(),
    val currentMonthMostExpenseCategory: CategoryGroupItem = CategoryGroupItem(
        Constants.transactionCategories[0],
        0f
    ),
    val currentMonthMostIncomeCategory: CategoryGroupItem = CategoryGroupItem(
        Constants.transactionCategories[0],
        0f
    )
)