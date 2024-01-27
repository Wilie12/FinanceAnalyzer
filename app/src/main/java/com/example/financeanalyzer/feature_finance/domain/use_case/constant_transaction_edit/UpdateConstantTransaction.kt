package com.example.financeanalyzer.feature_finance.domain.use_case.constant_transaction_edit

import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository

class UpdateConstantTransaction(
    private val repository: FinanceRepository
) {

    suspend operator fun invoke(
        id: Int,
        name: String,
        value: Float,
        transactionType: Int
    ) {
        repository.updateConstantTransaction(
            ConstantTransaction(
                id = id,
                name = name,
                value = value,
                transactionType = transactionType
            )
        )
    }
}