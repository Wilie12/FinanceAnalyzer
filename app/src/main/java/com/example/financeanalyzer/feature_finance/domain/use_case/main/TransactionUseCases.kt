package com.example.financeanalyzer.feature_finance.domain.use_case.main

import com.example.financeanalyzer.feature_finance.domain.use_case.GetConstantTransactions
import com.example.financeanalyzer.feature_finance.domain.use_case.GetFirstDayOfTheMonthInMillis
import com.example.financeanalyzer.feature_finance.domain.use_case.GetTransactions

data class TransactionUseCases(
    val getTransactions: GetTransactions,
    val getConstantTransactions: GetConstantTransactions,
    val getFirstDayOfTheMonthInMillis: GetFirstDayOfTheMonthInMillis
)