package com.example.financeanalyzer.feature_finance.presentation.add_transaction_screen

import com.example.financeanalyzer.feature_finance.data.util.Constants
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.model.TransactionCategory
import com.example.financeanalyzer.feature_finance.presentation.util.TransactionType

data class AddTransactionState(
    val name: String = "",
    val value: String = "",
    val transactionType: Int = Transaction.TYPE_EXPENSE,
    val type: Int = TransactionType.TYPE_NORMAL,
    val date: Long = 0L,
    val categoryExpense: TransactionCategory = Constants.transactionCategories[0],
    val categoryIncome: TransactionCategory = Constants.transactionCategories[9]
)
