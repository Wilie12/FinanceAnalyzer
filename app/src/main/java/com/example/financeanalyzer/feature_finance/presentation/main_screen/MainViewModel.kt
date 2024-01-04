package com.example.financeanalyzer.feature_finance.presentation.main_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.use_case.main.TransactionUseCases
import com.example.financeanalyzer.feature_finance.presentation.util.parseIntToMonthString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val transactionUseCases: TransactionUseCases
) : ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    init {
        getTransactions()
        getCurrentMonth()
    }

    private fun getTransactions() {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)

            val job = viewModelScope.launch {
                getTransactionsFromCurrentMonth()
                getConstantTransactions()
            }
            job.join()

            getIncomeAndExpenseFromCurrentMonth()

            _state.value = state.value.copy(isLoading = false)
        }
    }

    private fun getCurrentMonth() {
        val c = Calendar.getInstance()

        _state.value = state.value.copy(
            currentMonth = parseIntToMonthString(c.get(Calendar.MONTH))
        )
    }

    private fun getIncomeAndExpenseFromCurrentMonth() {

        val transactions = state.value.transactions
        val constantTransactions = state.value.constantTransactions

        var income = 0f
        var expense = 0f

        val transactionsExpense = transactions
            .filter { it.transactionType == Transaction.TYPE_EXPENSE }
            .sumOf { it.value.toDouble() }.toFloat()
        val transactionsIncome = transactions
            .filter { it.transactionType == Transaction.TYPE_INCOME }
            .sumOf { it.value.toDouble() }.toFloat()

        val constantTransactionsExpense = constantTransactions
            .filter { it.transactionType == ConstantTransaction.TYPE_EXPENSE }
            .sumOf { it.value.toDouble() }.toFloat()
        val constantTransactionsIncome = constantTransactions
            .filter { it.transactionType == ConstantTransaction.TYPE_INCOME }
            .sumOf { it.value.toDouble() }.toFloat()

        expense += transactionsExpense + constantTransactionsExpense
        income += transactionsIncome + constantTransactionsIncome

        _state.value = state.value.copy(
            expense = expense,
            income = income
        )
    }

    private suspend fun getTransactionsFromCurrentMonth() {

        val firstDayOfMonth = transactionUseCases.getFirstDayOfTheMonthInMillis()

        val transactions = transactionUseCases.getTransactions(firstDayOfMonth)

        _state.value = state.value.copy(transactions = transactions)
    }

    private suspend fun getConstantTransactions() {

        val constantTransactions = transactionUseCases.getConstantTransactions()

        _state.value = state.value.copy(constantTransactions = constantTransactions)
    }
}