package com.example.financeanalyzer.feature_finance.domain.use_case.main

import com.example.financeanalyzer.feature_finance.domain.use_case.GetConstantTransactions
import com.example.financeanalyzer.feature_finance.domain.use_case.GetFirstDayOfTheMonthInMillis
import com.example.financeanalyzer.feature_finance.domain.use_case.GetTransactions

data class MainUseCases(
    val getTransactions: GetTransactions,
    val getConstantTransactions: GetConstantTransactions,
    val getFirstDayOfTheMonthInMillis: GetFirstDayOfTheMonthInMillis
)