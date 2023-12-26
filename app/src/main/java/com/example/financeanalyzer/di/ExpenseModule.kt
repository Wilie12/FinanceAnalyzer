package com.example.financeanalyzer.di

import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository
import com.example.financeanalyzer.feature_finance.domain.use_case.ExpenseUseCases
import com.example.financeanalyzer.feature_finance.domain.use_case.GetAllTransactionsGroupedByCategoryFromCurrentMonth
import com.example.financeanalyzer.feature_finance.domain.use_case.GetConstantTransactions
import com.example.financeanalyzer.feature_finance.domain.use_case.GetTransactions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ExpenseModule {

    @Provides
    @ViewModelScoped
    fun provideExpenseUseCases(repository: FinanceRepository): ExpenseUseCases {
        return ExpenseUseCases(
            getAllTransactionsGroupedByCategoryFromCurrentMonth = GetAllTransactionsGroupedByCategoryFromCurrentMonth(repository),
            getConstantTransactions = GetConstantTransactions(repository)
        )
    }
}