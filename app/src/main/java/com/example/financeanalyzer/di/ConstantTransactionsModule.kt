package com.example.financeanalyzer.di

import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository
import com.example.financeanalyzer.feature_finance.domain.use_case.GetConstantTransactions
import com.example.financeanalyzer.feature_finance.domain.use_case.constant_transactions.ConstantTransactionsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ConstantTransactionsModule {

    @Provides
    @ViewModelScoped
    fun provideConstantTransactionsUseCases(repository: FinanceRepository): ConstantTransactionsUseCases {
        return ConstantTransactionsUseCases(
            getConstantTransactions = GetConstantTransactions(repository)
        )
    }
}