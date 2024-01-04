package com.example.financeanalyzer.feature_finance.domain.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

// TODO - Try adding second color(darker or lighter) for NormalCategoryScreen

data class TransactionCategory(
    val id: Int,
    val name: String,
    @DrawableRes
    val icon: Int,
    val color: Color,
    val transactionType: Int
) {

    companion object {
        const val TYPE_INCOME = 1
        const val TYPE_EXPENSE = 2
    }
}
