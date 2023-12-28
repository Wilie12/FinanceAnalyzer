package com.example.financeanalyzer.feature_finance.domain.use_case.add_transaction

import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.model.TransactionCategory
import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository

class AddTransaction(
    private val repository: FinanceRepository
) {

    suspend operator fun invoke(
        transactionType: Int,
        name: String,
        value: Float,
        date: Long,
        category: TransactionCategory
    ) {
        repository.addTransaction(
            Transaction(
                id = 0,
                transactionType = transactionType,
                name = name,
                value = value,
                date = date,
                category = category
            )
        )
    }
}