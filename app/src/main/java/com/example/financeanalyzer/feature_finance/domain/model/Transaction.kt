package com.example.financeanalyzer.feature_finance.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: Long,
    val category: TransactionCategory,
    val value: Float,
    val comment: String,
    val transactionType: Int
) {

    companion object {
        const val TYPE_INCOME = 1
        const val TYPE_EXPENSE = 2
    }
}