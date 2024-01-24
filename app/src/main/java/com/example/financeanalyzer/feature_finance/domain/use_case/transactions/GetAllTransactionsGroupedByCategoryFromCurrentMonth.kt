package com.example.financeanalyzer.feature_finance.domain.use_case.transactions

import com.example.financeanalyzer.feature_finance.domain.model.CategoryGroupItem
import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository

class GetAllTransactionsGroupedByCategoryFromCurrentMonth(
    private val repository: FinanceRepository
) {

    suspend operator fun invoke(
        firstDayOfMonth: Long,
        transactionType: Int
    ): List<CategoryGroupItem> {
        return repository.getAllTransactionsGroupedByCategoryFromCurrentMonth(firstDayOfMonth, transactionType)
    }
}