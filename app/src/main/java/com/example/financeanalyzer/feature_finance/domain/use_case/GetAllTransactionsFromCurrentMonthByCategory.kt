package com.example.financeanalyzer.feature_finance.domain.use_case

import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.model.TransactionCategory
import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository

class GetAllTransactionsFromCurrentMonthByCategory(private val repository: FinanceRepository) {

    suspend operator fun invoke(
        firstDayOfMonth: Long,
        category: TransactionCategory
    ): List<Transaction> {
        return repository.getAllTransactionsFromCurrentMonthByCategory(firstDayOfMonth, category)
    }
}