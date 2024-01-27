package com.example.financeanalyzer.feature_finance.presentation.transactions_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeanalyzer.feature_finance.domain.model.CategoryGroupItem
import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.use_case.transactions.TransactionsUseCases
import com.example.financeanalyzer.feature_finance.presentation.util.parseIntToMonthString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val transactionsUseCases: TransactionsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(TransactionsState())
    val state: State<TransactionsState> = _state

    init {
        getCurrentMonth()
        if (checkNotNull(savedStateHandle["transactionType"]) == Transaction.TYPE_EXPENSE) {
            getExpenses()
            _state.value = state.value.copy(transactionType = Transaction.TYPE_EXPENSE)
        } else {
            getIncomes()
            _state.value = state.value.copy(transactionType = Transaction.TYPE_INCOME)
        }
    }

    private fun getCurrentMonth() {
        val c = Calendar.getInstance()

        _state.value = state.value.copy(
            currentMonth = parseIntToMonthString(c.get(Calendar.MONTH))
        )
    }

    private fun getExpenses() {
        viewModelScope.launch {

            _state.value = state.value.copy(isLoading = true)

            val job = viewModelScope.launch {
                getExpensesByCategoryFromCurrentMonth()
            }
            job.join()

            getExpenseFromCurrentMonth()

            _state.value = state.value.copy(isLoading = false)
        }
    }

    private fun getIncomes() {
        viewModelScope.launch {

            _state.value = state.value.copy(isLoading = true)

            val job = viewModelScope.launch {
                getIncomesByCategoryFromCurrentMonth()
            }
            job.join()

            getIncomeFromCurrentMonth()

            _state.value = state.value.copy(isLoading = false)
        }
    }

    private suspend fun getExpenseFromCurrentMonth() {

        val constantTransactions = transactionsUseCases.getConstantTransactions()
        val listOfCategoryExpenses = state.value.categoryGroupItems

        val normalExpense = listOfCategoryExpenses.sumOf { it.value.toDouble() }.toFloat()
        val constantExpense = constantTransactions
            .filter { it.transactionType == ConstantTransaction.TYPE_EXPENSE }
            .sumOf { it.value.toDouble() }.toFloat()

        _state.value = state.value.copy(
            normalValue = normalExpense,
            constantValue = constantExpense
        )

    }

    private suspend fun getIncomeFromCurrentMonth() {

        val constantTransactions = transactionsUseCases.getConstantTransactions()
        val listOfCategoryIncomes = state.value.categoryGroupItems

        val normalIncome = listOfCategoryIncomes.sumOf { it.value.toDouble() }.toFloat()
        val constantIncome = constantTransactions
            .filter { it.transactionType == ConstantTransaction.TYPE_INCOME }
            .sumOf { it.value.toDouble() }.toFloat()

        _state.value = state.value.copy(
            normalValue = normalIncome,
            constantValue = constantIncome
        )

    }

    private suspend fun getExpensesByCategoryFromCurrentMonth() {

        val listOfCategoryExpenses = mutableListOf<CategoryGroupItem>()

        val firstDayOfMonth = transactionsUseCases.getFirstDayOfTheMonthInMillis()

        transactionsUseCases.getAllTransactionsGroupedByCategoryFromCurrentMonth(
            firstDayOfMonth, Transaction.TYPE_EXPENSE
        ).groupBy { categoryGroupItem -> categoryGroupItem.category }
            .forEach { (transactionCategory, listOfCategoryGroupItems) ->
                val item = CategoryGroupItem(
                    category = transactionCategory,
                    value = listOfCategoryGroupItems.sumOf { it.value.toDouble() }.toFloat()
                )
                listOfCategoryExpenses.add(item)
            }

        _state.value = state.value.copy(categoryGroupItems = listOfCategoryExpenses)
    }

    private suspend fun getIncomesByCategoryFromCurrentMonth() {

        val listOfCategoryIncomes = mutableListOf<CategoryGroupItem>()

        val firstDayOfMonth = transactionsUseCases.getFirstDayOfTheMonthInMillis()

        transactionsUseCases.getAllTransactionsGroupedByCategoryFromCurrentMonth(
            firstDayOfMonth, Transaction.TYPE_INCOME
        ).groupBy { categoryGroupItem -> categoryGroupItem.category }
            .forEach { (transactionCategory, listOfCategoryGroupItems) ->
                val item = CategoryGroupItem(
                    category = transactionCategory,
                    value = listOfCategoryGroupItems.sumOf { it.value.toDouble() }.toFloat()
                )
                listOfCategoryIncomes.add(item)
            }

        _state.value = state.value.copy(categoryGroupItems = listOfCategoryIncomes)
    }
}