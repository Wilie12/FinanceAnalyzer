package com.example.financeanalyzer.di

import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository
import com.example.financeanalyzer.feature_finance.domain.use_case.GetFirstDayOfTheMonthInMillis
import com.example.financeanalyzer.feature_finance.domain.use_case.add_transaction.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AddTransactionModule {

    @Provides
    @ViewModelScoped
    fun provideAddTransactionUseCases(repository: FinanceRepository): AddTransactionUseCases {
        return AddTransactionUseCases(
            getFirstDayOfTheMonthInMillis = GetFirstDayOfTheMonthInMillis(),
            getLastDayOfTheMonthInMillis = GetLastDayOfTheMonthInMillis(),
            getSelectedDateInMillis = GetSelectedDateInMillis(),
            addTransaction = AddTransaction(repository),
            addConstantTransaction = AddConstantTransaction(repository)
        )
    }
}