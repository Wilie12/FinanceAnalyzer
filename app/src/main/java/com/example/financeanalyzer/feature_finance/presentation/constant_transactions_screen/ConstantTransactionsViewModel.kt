package com.example.financeanalyzer.feature_finance.presentation.constant_transactions_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import com.example.financeanalyzer.feature_finance.domain.use_case.constant_transactions.ConstantTransactionsUseCases
import com.example.financeanalyzer.feature_finance.presentation.util.parseIntToMonthString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ConstantTransactionsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val constantTransactionsUseCases: ConstantTransactionsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(ConstantTransactionsState())
    val state: State<ConstantTransactionsState> = _state

    init {
        setStateTransactionType(checkNotNull(savedStateHandle["transactionType"]))
        getCurrentMonth()
        setUp()
        Log.d("CONSTANTS_TRANSACTIONS", "state list: ${state.value.constantTransactions}")
        Log.d("CONSTANTS_TRANSACTIONS", "state value: ${state.value.totalConstantExpense}")
    }

    private fun getCurrentMonth() {
        val c = Calendar.getInstance()

        _state.value = state.value.copy(
            currentMonth = parseIntToMonthString(c.get(Calendar.MONTH))
        )
    }

    private fun setUp() {
        viewModelScope.launch {
            val job = viewModelScope.launch {
                getConstantTransactions(state.value.transactionType)
            }
            job.join()

            getTotalConstantValue(state.value.constantTransactions)
        }
    }

    private suspend fun getConstantTransactions(transactionType: Int) {

        val list = when (transactionType) {
            ConstantTransaction.TYPE_EXPENSE -> {
                constantTransactionsUseCases.getConstantTransactions()
                    .filter { it.transactionType == ConstantTransaction.TYPE_EXPENSE }
            }
            ConstantTransaction.TYPE_INCOME -> {
                constantTransactionsUseCases.getConstantTransactions()
                    .filter { it.transactionType == ConstantTransaction.TYPE_INCOME }
            }
            else -> emptyList()
        }

        _state.value = state.value.copy(
            constantTransactions = list
        )
    }

    private fun getTotalConstantValue(constantTransactions: List<ConstantTransaction>) {

        val totalValue = constantTransactions.sumOf { it.value.toDouble() }.toFloat()

        when (state.value.transactionType) {
            ConstantTransaction.TYPE_EXPENSE -> {
                _state.value = state.value.copy(
                    totalConstantExpense = totalValue
                )
            }
            ConstantTransaction.TYPE_INCOME -> {
                _state.value = state.value.copy(
                    totalConstantIncome = totalValue
                )
            }
        }
    }

    private fun setStateTransactionType(transactionType: Int) {
        _state.value = state.value.copy(
            transactionType = transactionType
        )
    }
}