package com.example.financeanalyzer.feature_finance.data.repository

import com.example.financeanalyzer.feature_finance.data.data_source.FinanceDao
import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository
import kotlinx.coroutines.flow.Flow

class FinanceRepositoryImpl(
    private val financeDao: FinanceDao
): FinanceRepository {

    override suspend fun getAllTransactionsFromCurrentMonth(firstDayOfMonth: Long): List<Transaction> {
        return financeDao.getAllTransactionsFromCurrentMonth(firstDayOfMonth)
    }

    override suspend fun getAllConstantTransactions(): List<ConstantTransaction> {
        return financeDao.getAllConstantTransactions()
    }

    override suspend fun addTransaction(transaction: Transaction) {
        financeDao.addTransaction(transaction)
    }
}