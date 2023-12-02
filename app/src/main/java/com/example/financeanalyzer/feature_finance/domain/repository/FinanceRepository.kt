package com.example.financeanalyzer.feature_finance.domain.repository

import com.example.financeanalyzer.feature_finance.domain.model.Transaction

interface FinanceRepository {

    suspend fun getAllTransactionsFromCurrentMonth(firstDayOfMonth: Long): List<Transaction>

    suspend fun addTransaction(transaction: Transaction)
}