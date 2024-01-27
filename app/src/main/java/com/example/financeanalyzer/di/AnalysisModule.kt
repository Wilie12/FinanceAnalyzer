package com.example.financeanalyzer.di

import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository
import com.example.financeanalyzer.feature_finance.domain.use_case.GetAllTransactionsGroupedByCategoryFromCurrentMonth
import com.example.financeanalyzer.feature_finance.domain.use_case.GetFirstDayOfTheMonthInMillis
import com.example.financeanalyzer.feature_finance.domain.use_case.GetTransactions
import com.example.financeanalyzer.feature_finance.domain.use_case.analysis.AnalysisUseCases
import com.example.financeanalyzer.feature_finance.domain.use_case.analysis.GetFirstDayOfThePreviousMonthInMillis
import com.example.financeanalyzer.feature_finance.domain.use_case.analysis.GetTransactionsFromPreviousMonth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AnalysisModule {

    @Provides
    @ViewModelScoped
    fun provideAnalysisUseCases(repository: FinanceRepository): AnalysisUseCases {
        return AnalysisUseCases(
            getFirstDayOfThePreviousMonthInMillis = GetFirstDayOfThePreviousMonthInMillis(),
            getFirstDayOfTheMonthInMillis = GetFirstDayOfTheMonthInMillis(),
            getTransactions = GetTransactions(repository),
            getTransactionsFromPreviousMonth = GetTransactionsFromPreviousMonth(repository),
            getAllTransactionsGroupedByCategoryFromCurrentMonth = GetAllTransactionsGroupedByCategoryFromCurrentMonth(repository)
        )
    }
}