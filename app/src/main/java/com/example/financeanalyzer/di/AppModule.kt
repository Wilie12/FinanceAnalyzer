package com.example.financeanalyzer.di

import android.app.Application
import androidx.room.Room
import com.example.financeanalyzer.feature_finance.data.data_source.FinanceDatabase
import com.example.financeanalyzer.feature_finance.data.repository.FinanceRepositoryImpl
import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository
import com.example.financeanalyzer.feature_finance.domain.use_case.GetConstantTransactions
import com.example.financeanalyzer.feature_finance.domain.use_case.GetTransactions
import com.example.financeanalyzer.feature_finance.domain.use_case.TransactionUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFinanceDatabase(app: Application): FinanceDatabase {
        return Room.databaseBuilder(
            app,
            FinanceDatabase::class.java,
            FinanceDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideFinanceRepository(db: FinanceDatabase): FinanceRepository {
        return FinanceRepositoryImpl(db.financeDao)
    }

    @Provides
    @Singleton
    fun provideTransactionUseCases(repository: FinanceRepository): TransactionUseCases {
        return TransactionUseCases(
            getTransactions = GetTransactions(repository),
            getConstantTransactions = GetConstantTransactions(repository)
        )
    }
}