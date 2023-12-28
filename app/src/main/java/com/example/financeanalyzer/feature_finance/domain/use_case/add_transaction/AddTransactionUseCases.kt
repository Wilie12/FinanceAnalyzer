package com.example.financeanalyzer.feature_finance.domain.use_case.add_transaction

import com.example.financeanalyzer.feature_finance.domain.use_case.GetFirstDayOfTheMonthInMillis

data class AddTransactionUseCases(
    val getFirstDayOfTheMonthInMillis: GetFirstDayOfTheMonthInMillis,
    val getLastDayOfTheMonthInMillis: GetLastDayOfTheMonthInMillis,
    val getSelectedDateInMillis: GetSelectedDateInMillis,
    val addTransaction: AddTransaction,
    val addConstantTransaction: AddConstantTransaction
)
