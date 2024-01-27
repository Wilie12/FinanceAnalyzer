package com.example.financeanalyzer.feature_finance.domain.use_case.analysis

import com.example.financeanalyzer.feature_finance.domain.model.CategoryGroupItem
import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository

class GetAllTransactionsGroupedByCategoryFromPreviousMonth(
    private val repository: FinanceRepository
) {

    suspend operator fun invoke(
        firstDayOfPreviousMonth: Long,
        firstDayOfMonth: Long,
        transactionType: Int
    ): List<CategoryGroupItem> {
        return repository.getAllTransactionsGroupedByCategoryFromPreviousMonth(
            firstDayOfPreviousMonth,
            firstDayOfMonth,
            transactionType
        )
    }
}