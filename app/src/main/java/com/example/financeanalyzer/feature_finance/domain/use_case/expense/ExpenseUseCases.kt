package com.example.financeanalyzer.feature_finance.domain.use_case.expense

import com.example.financeanalyzer.feature_finance.domain.use_case.GetConstantTransactions
import com.example.financeanalyzer.feature_finance.domain.use_case.GetFirstDayOfTheMonthInMillis

data class ExpenseUseCases(
    val getAllTransactionsGroupedByCategoryFromCurrentMonth: GetAllTransactionsGroupedByCategoryFromCurrentMonth,
    val getConstantTransactions: GetConstantTransactions,
    val getFirstDayOfTheMonthInMillis: GetFirstDayOfTheMonthInMillis
)
