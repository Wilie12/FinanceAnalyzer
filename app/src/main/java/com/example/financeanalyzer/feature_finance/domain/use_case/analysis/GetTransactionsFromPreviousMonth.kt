package com.example.financeanalyzer.feature_finance.domain.use_case.analysis

import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository

class GetTransactionsFromPreviousMonth(private val repository: FinanceRepository) {

    suspend operator fun invoke(
        firstDayOfPreviousMonth: Long,
        firstDayOfMonth: Long
    ): List<Transaction> {
        return repository.getAllTransactionsFromPreviousMonth(firstDayOfPreviousMonth, firstDayOfMonth)
    }
}