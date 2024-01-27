package com.example.financeanalyzer.feature_finance.domain.use_case.add_transaction

import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository

class AddConstantTransaction(
    private val repository: FinanceRepository
) {

    suspend operator fun invoke(
        transactionType: Int,
        name: String,
        value: Float,
    ) {
        repository.addConstantTransaction(
            ConstantTransaction(
                id = 0,
                transactionType = transactionType,
                name = name,
                value = value
            )
        )
    }
}