package com.example.financeanalyzer.feature_finance.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "constant_income")
data class ConstantIncome(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val value: Float
)
