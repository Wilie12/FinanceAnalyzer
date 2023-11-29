package com.example.financeanalyzer.feature_finance.data.util

import androidx.compose.ui.graphics.Color
import com.example.financeanalyzer.R
import com.example.financeanalyzer.feature_finance.domain.model.TransactionCategory

object Constants {

    val transactionCategories = listOf<TransactionCategory>(
        TransactionCategory(
            id = 0,
            name = "Zakupy",
            icon = R.drawable.ic_shopping,
            color = Color.Red,
            transactionType = TransactionCategory.TYPE_EXPENSE
        ),
        TransactionCategory(
            id = 1,
            name = "Inne",
            icon = R.drawable.ic_other,
            color = Color.Gray,
            transactionType = TransactionCategory.TYPE_INCOME
        )
    )
}