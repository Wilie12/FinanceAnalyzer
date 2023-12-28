package com.example.financeanalyzer.di

import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository
import com.example.financeanalyzer.feature_finance.domain.use_case.GetConstantTransactions
import com.example.financeanalyzer.feature_finance.domain.use_case.GetFirstDayOfTheMonthInMillis
import com.example.financeanalyzer.feature_finance.domain.use_case.GetTransactions
import com.example.financeanalyzer.feature_finance.domain.use_case.main.TransactionUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MainModule {

    @Provides
    @ViewModelScoped
    fun provideTransactionUseCases(repository: FinanceRepository): TransactionUseCases {
        return TransactionUseCases(
            getTransactions = GetTransactions(repository),
            getConstantTransactions = GetConstantTransactions(repository),
            getFirstDayOfTheMonthInMillis = GetFirstDayOfTheMonthInMillis()
        )
    }
}