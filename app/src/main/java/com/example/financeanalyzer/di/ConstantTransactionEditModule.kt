package com.example.financeanalyzer.di

import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository
import com.example.financeanalyzer.feature_finance.domain.use_case.constant_transaction_edit.ConstantTransactionEditUseCases
import com.example.financeanalyzer.feature_finance.domain.use_case.constant_transaction_edit.GetConstantTransaction
import com.example.financeanalyzer.feature_finance.domain.use_case.constant_transaction_edit.UpdateConstantTransaction
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ConstantTransactionEditModule {

    @Provides
    @ViewModelScoped
    fun provideConstantTransactionEditUseCases(repository: FinanceRepository): ConstantTransactionEditUseCases {
        return ConstantTransactionEditUseCases(
            getConstantTransaction = GetConstantTransaction(repository),
            updateConstantTransaction = UpdateConstantTransaction(repository)
        )
    }
}