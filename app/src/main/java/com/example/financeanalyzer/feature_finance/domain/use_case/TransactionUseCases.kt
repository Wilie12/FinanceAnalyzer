package com.example.financeanalyzer.feature_finance.domain.use_case

data class TransactionUseCases(
    val getTransactions: GetTransactions,
    val getConstantTransactions: GetConstantTransactions
)