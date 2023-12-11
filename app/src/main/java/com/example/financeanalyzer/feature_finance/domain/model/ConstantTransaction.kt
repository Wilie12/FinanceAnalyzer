package com.example.financeanalyzer.feature_finance.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "constant_transaction_table")
data class ConstantTransaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val value: Float,
    val transactionType: Int
) {

    companion object {
        const val TYPE_INCOME = 1
        const val TYPE_EXPENSE = 2
    }
}
