package com.example.financeanalyzer.feature_finance.domain.use_case.constant_transaction_edit

import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository

class GetConstantTransaction(
    private val repository: FinanceRepository
) {

    suspend operator fun invoke(id: Int): ConstantTransaction {
        return repository.getConstantTransaction(id)
    }
}