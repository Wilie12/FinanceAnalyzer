package com.example.financeanalyzer.feature_finance.data.util

import androidx.compose.ui.graphics.Color
import com.example.financeanalyzer.R
import com.example.financeanalyzer.feature_finance.domain.model.TransactionCategory

object Constants {

    val transactionCategories = listOf<TransactionCategory>(
        TransactionCategory(
            id = 0,
            name = "Zakupy",
            icon = R.drawable.ic_shop,
            color = Color(0xFF1565C0),
            transactionType = TransactionCategory.TYPE_EXPENSE

        ),
        TransactionCategory(
            id = 1,
            name = "Mieszkanie",
            icon = R.drawable.ic_house,
            color = Color(0xFF558B2F),
            transactionType = TransactionCategory.TYPE_EXPENSE
        ),
        TransactionCategory(
            id = 2,
            name = "Transport",
            icon = R.drawable.ic_transport,
            color = Color(0xFF00838F),
            transactionType = TransactionCategory.TYPE_EXPENSE
        ),
        TransactionCategory(
            id = 3,
            name = "Zdrowie",
            icon = R.drawable.ic_health,
            color = Color(0xFF2E7D32),
            transactionType = TransactionCategory.TYPE_EXPENSE
        ),
        TransactionCategory(
            id = 4,
            name = "Rozrywka",
            icon = R.drawable.ic_theater,
            color = Color(0xFF6A1B9A),
            transactionType = TransactionCategory.TYPE_EXPENSE
        ),
        TransactionCategory(
            id = 5,
            name = "Odzież",
            icon = R.drawable.ic_clothes,
            color = Color(0xFFD84315),
            transactionType = TransactionCategory.TYPE_EXPENSE
        ),
        TransactionCategory(
            id = 6,
            name = "Edukacja",
            icon = R.drawable.ic_education,
            color = Color(0xFFC62828),
            transactionType = TransactionCategory.TYPE_EXPENSE
        ),
        TransactionCategory(
            id = 7,
            name = "Oszczędności",
            icon = R.drawable.ic_savings,
            color = Color(0xFFF9A825),
            transactionType = TransactionCategory.TYPE_EXPENSE
        ),
        TransactionCategory(
            id = 8,
            name = "Inne",
            icon = R.drawable.ic_dots,
            color = Color.Gray,
            transactionType = TransactionCategory.TYPE_EXPENSE
        ),
        TransactionCategory(
            id = 9,
            name = "Prezent",
            icon = R.drawable.ic_gift,
            color = Color.Gray,
            transactionType = TransactionCategory.TYPE_INCOME
        ),
        TransactionCategory(
            id = 10,
            name = "Inne",
            icon = R.drawable.ic_dots,
            color = Color.Gray,
            transactionType = TransactionCategory.TYPE_INCOME
        )
    )
}