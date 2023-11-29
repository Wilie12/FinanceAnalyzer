package com.example.financeanalyzer.feature_finance.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.financeanalyzer.feature_finance.data.util.Converters
import com.example.financeanalyzer.feature_finance.domain.model.ConstantIncome
import com.example.financeanalyzer.feature_finance.domain.model.Transaction

@Database(
    entities = [Transaction::class, ConstantIncome::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class FinanceDatabase: RoomDatabase() {

    abstract val financeDao: FinanceDao

    companion object {

        const val DATABASE_NAME = "finance_db"
    }
}