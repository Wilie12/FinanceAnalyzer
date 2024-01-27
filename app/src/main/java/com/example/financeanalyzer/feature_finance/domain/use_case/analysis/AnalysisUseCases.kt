package com.example.financeanalyzer.feature_finance.domain.use_case.analysis

import com.example.financeanalyzer.feature_finance.domain.use_case.GetAllTransactionsGroupedByCategoryFromCurrentMonth
import com.example.financeanalyzer.feature_finance.domain.use_case.GetFirstDayOfTheMonthInMillis
import com.example.financeanalyzer.feature_finance.domain.use_case.GetTransactions

data class AnalysisUseCases(
    val getFirstDayOfThePreviousMonthInMillis: GetFirstDayOfThePreviousMonthInMillis,
    val getFirstDayOfTheMonthInMillis: GetFirstDayOfTheMonthInMillis,
    val getTransactions: GetTransactions,
    val getTransactionsFromPreviousMonth: GetTransactionsFromPreviousMonth,
    val getAllTransactionsGroupedByCategoryFromCurrentMonth: GetAllTransactionsGroupedByCategoryFromCurrentMonth
)