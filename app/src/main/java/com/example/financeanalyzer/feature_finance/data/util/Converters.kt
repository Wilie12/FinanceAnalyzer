package com.example.financeanalyzer.feature_finance.data.util

import androidx.room.TypeConverter
import com.example.financeanalyzer.feature_finance.domain.model.TransactionCategory

class Converters {

    @TypeConverter
    fun transactionCategoryToInt(transactionCategory: TransactionCategory): Int {
        return transactionCategory.id
    }

    @TypeConverter
    fun intToTransactionCategory(id: Int): TransactionCategory {
        return Constants.transactionCategories[id]
    }
}