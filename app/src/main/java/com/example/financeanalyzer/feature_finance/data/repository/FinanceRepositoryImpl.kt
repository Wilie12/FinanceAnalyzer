package com.example.financeanalyzer.feature_finance.data.repository

import com.example.financeanalyzer.feature_finance.data.data_source.FinanceDao
import com.example.financeanalyzer.feature_finance.domain.model.CategoryGroupItem
import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.model.TransactionCategory
import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository

class FinanceRepositoryImpl(
    private val financeDao: FinanceDao
): FinanceRepository {

    override suspend fun getAllTransactionsGroupedByCategoryFromCurrentMonth(
        firstDayOfMonth: Long,
        transactionType: Int
    ): List<CategoryGroupItem> {
        return financeDao.getAllTransactionsGroupedByCategoryFromCurrentMonth(firstDayOfMonth, transactionType)
    }

    override suspend fun getAllTransactionsFromCurrentMonth(firstDayOfMonth: Long): List<Transaction> {
        return financeDao.getAllTransactionsFromCurrentMonth(firstDayOfMonth)
    }

    override suspend fun getAllTransactionsFromCurrentMonthByCategory(
        firstDayOfMonth: Long,
        category: TransactionCategory
    ): List<Transaction> {
        return financeDao.getAllTransactionsFromCurrentMonthByCategory(firstDayOfMonth, category)
    }

    override suspend fun getAllConstantTransactions(): List<ConstantTransaction> {
        return financeDao.getAllConstantTransactions()
    }

    override suspend fun addTransaction(transaction: Transaction) {
        financeDao.addTransaction(transaction)
    }

    override suspend fun addConstantTransaction(constantTransaction: ConstantTransaction) {
        financeDao.addConstantTransaction(constantTransaction)
    }
}