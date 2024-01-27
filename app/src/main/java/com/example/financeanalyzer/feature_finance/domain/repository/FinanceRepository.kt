package com.example.financeanalyzer.feature_finance.domain.repository

import com.example.financeanalyzer.feature_finance.domain.model.CategoryGroupItem
import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.model.TransactionCategory

interface FinanceRepository {

    suspend fun getConstantTransaction(id: Int): ConstantTransaction

    suspend fun getAllTransactionsGroupedByCategoryFromCurrentMonth(
        firstDayOfMonth: Long,
        transactionType: Int
    ): List<CategoryGroupItem>

    suspend fun getAllTransactionsGroupedByCategoryFromPreviousMonth(
        firstDayOfPreviousMonth: Long,
        firstDayOfMonth: Long,
        transactionType: Int
    ): List<CategoryGroupItem>

    suspend fun getAllTransactionsFromCurrentMonth(firstDayOfMonth: Long): List<Transaction>

    suspend fun getAllTransactionsFromPreviousMonth(
        firstDayOfPreviousMonth: Long,
        firstDayOfMonth: Long
    ): List<Transaction>

    suspend fun getAllTransactionsFromCurrentMonthByCategory(
        firstDayOfMonth: Long,
        category: TransactionCategory
    ): List<Transaction>

    suspend fun getAllConstantTransactions(): List<ConstantTransaction>

    suspend fun addTransaction(transaction: Transaction)

    suspend fun addConstantTransaction(constantTransaction: ConstantTransaction)

    suspend fun updateConstantTransaction(constantTransaction: ConstantTransaction)
}