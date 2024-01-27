package com.example.financeanalyzer.feature_finance.domain.use_case

import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository

class GetConstantTransactions(
    private val repository: FinanceRepository
) {

    suspend operator fun invoke(): List<ConstantTransaction> {
        return repository.getAllConstantTransactions()
    }
}