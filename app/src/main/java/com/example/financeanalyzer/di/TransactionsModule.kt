package com.example.financeanalyzer.di

import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository
import com.example.financeanalyzer.feature_finance.domain.use_case.transactions.TransactionsUseCases
import com.example.financeanalyzer.feature_finance.domain.use_case.transactions.GetAllTransactionsGroupedByCategoryFromCurrentMonth
import com.example.financeanalyzer.feature_finance.domain.use_case.GetConstantTransactions
import com.example.financeanalyzer.feature_finance.domain.use_case.GetFirstDayOfTheMonthInMillis
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TransactionsModule {

    @Provides
    @ViewModelScoped
    fun provideExpenseUseCases(repository: FinanceRepository): TransactionsUseCases {
        return TransactionsUseCases(
            getAllTransactionsGroupedByCategoryFromCurrentMonth = GetAllTransactionsGroupedByCategoryFromCurrentMonth(repository),
            getConstantTransactions = GetConstantTransactions(repository),
            getFirstDayOfTheMonthInMillis = GetFirstDayOfTheMonthInMillis()
        )
    }
}