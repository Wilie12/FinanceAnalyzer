package com.example.financeanalyzer.feature_finance.domain.use_case

import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository

class GetTransactions(
    private val repository: FinanceRepository
) {

    suspend operator fun invoke(firstDayOfMonth: Long): List<Transaction> {
        return repository.getAllTransactionsFromCurrentMonth(firstDayOfMonth)
    }
}