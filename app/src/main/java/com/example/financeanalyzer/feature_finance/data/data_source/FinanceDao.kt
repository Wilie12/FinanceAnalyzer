package com.example.financeanalyzer.feature_finance.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.financeanalyzer.feature_finance.domain.model.CategoryGroupItem
import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import com.example.financeanalyzer.feature_finance.domain.model.Transaction

@Dao
interface FinanceDao {

    @Query("SELECT category, value FROM transaction_table " +
            "WHERE date >= :firstDayOfMonth AND transactionType = :transactionType")
    suspend fun getAllTransactionsGroupedByCategoryFromCurrentMonth(
        firstDayOfMonth: Long,
        transactionType: Int
    ): List<CategoryGroupItem>

    @Query("SELECT * FROM transaction_table WHERE date >= :firstDayOfMonth")
    suspend fun getAllTransactionsFromCurrentMonth(firstDayOfMonth: Long): List<Transaction>

    @Query("SELECT * FROM constant_transaction_table")
    suspend fun getAllConstantTransactions(): List<ConstantTransaction>

    @Insert
    suspend fun addTransaction(transaction: Transaction)
}