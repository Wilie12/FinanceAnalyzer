package com.example.financeanalyzer.feature_finance.data.repository

import com.example.financeanalyzer.feature_finance.data.data_source.FinanceDao
import com.example.financeanalyzer.feature_finance.domain.model.CategoryGroupItem
import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.model.TransactionCategory
import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository

class FinanceRepositoryImpl(
    private val financeDao: FinanceDao
) : FinanceRepository {

    override suspend fun getConstantTransaction(id: Int): ConstantTransaction {
        return financeDao.getConstantTransaction(id)
    }

    override suspend fun getAllTransactionsGroupedByCategoryFromCurrentMonth(
        firstDayOfMonth: Long,
        transactionType: Int
    ): List<CategoryGroupItem> {
        return financeDao.getAllTransactionsGroupedByCategoryFromCurrentMonth(
            firstDayOfMonth,
            transactionType
        )
    }

    override suspend fun getAllTransactionsGroupedByCategoryFromPreviousMonth(
        firstDayOfPreviousMonth: Long,
        firstDayOfMonth: Long,
        transactionType: Int
    ): List<CategoryGroupItem> {
        return financeDao.getAllTransactionsGroupedByCategoryFromPreviousMonth(
            firstDayOfPreviousMonth,
            firstDayOfMonth,
            transactionType
        )
    }

    override suspend fun getAllTransactionsFromCurrentMonth(firstDayOfMonth: Long): List<Transaction> {
        return financeDao.getAllTransactionsFromCurrentMonth(firstDayOfMonth)
    }

    override suspend fun getAllTransactionsFromPreviousMonth(
        firstDayOfPreviousMonth: Long,
        firstDayOfMonth: Long
    ): List<Transaction> {
        return financeDao.getAllTransactionsFromPreviousMonth(
            firstDayOfPreviousMonth,
            firstDayOfMonth
        )
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

    override suspend fun updateConstantTransaction(constantTransaction: ConstantTransaction) {
        financeDao.updateConstantTransaction(constantTransaction)
    }
}