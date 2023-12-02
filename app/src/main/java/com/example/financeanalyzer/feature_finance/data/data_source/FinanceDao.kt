package com.example.financeanalyzer.feature_finance.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.financeanalyzer.feature_finance.domain.model.Transaction

@Dao
interface FinanceDao {

    @Query("SELECT * FROM transaction_table WHERE date >= :firstDayOfMonth")
    suspend fun getAllTransactionsFromCurrentMonth(firstDayOfMonth: Long): List<Transaction>

    @Insert
    suspend fun addTransaction(transaction: Transaction)
}