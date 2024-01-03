package com.example.financeanalyzer.di

import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository
import com.example.financeanalyzer.feature_finance.domain.use_case.GetAllTransactionsFromCurrentMonthByCategory
import com.example.financeanalyzer.feature_finance.domain.use_case.GetFirstDayOfTheMonthInMillis
import com.example.financeanalyzer.feature_finance.domain.use_case.normal_category.NormalCategoryUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object NormalTransactionModule {

    @Provides
    @ViewModelScoped
    fun provideNormalCategoryUseCases(repository: FinanceRepository): NormalCategoryUseCases {
        return NormalCategoryUseCases(
            getAllTransactionsFromCurrentMonthByCategory = GetAllTransactionsFromCurrentMonthByCategory(repository),
            getFirstDayOfTheMonthInMillis = GetFirstDayOfTheMonthInMillis()
        )
    }
}