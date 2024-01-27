package com.example.financeanalyzer.feature_finance.presentation.analysis_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeanalyzer.feature_finance.domain.model.CategoryGroupItem
import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.use_case.analysis.AnalysisUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalysisViewModel @Inject constructor(
    private val analysisUseCases: AnalysisUseCases
): ViewModel() {

    private val _state = mutableStateOf(AnalysisState())
    val state: State<AnalysisState> = _state

    init {
        getTransactions()
    }

    private fun getTransactions() {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)

            val job = viewModelScope.launch {
                getTransactionsFromCurrentMonth()
                getTransactionsFromPreviousMonth()
            }
            job.join()

            getIncomeAndExpenseFromCurrentMonth()
            getIncomeAndExpenseFromPreviousMonth()

            getMostExpenseByCategoryFromCurrentMonth()
            getMostIncomeByCategoryFromCurrentMonth()

            _state.value = state.value.copy(isLoading = false)
        }
    }

    private fun getIncomeAndExpenseFromCurrentMonth() {

        val transactions = state.value.currentMonthTransactions

        var income = 0f
        var expense = 0f

        val transactionsExpense = transactions
            .filter { it.transactionType == Transaction.TYPE_EXPENSE }
            .sumOf { it.value.toDouble() }.toFloat()
        val transactionsIncome = transactions
            .filter { it.transactionType == Transaction.TYPE_INCOME }
            .sumOf { it.value.toDouble() }.toFloat()

        expense += transactionsExpense
        income += transactionsIncome

        _state.value = state.value.copy(
            currentMonthExpense = expense,
            currentMonthIncome = income
        )
    }

    private fun getIncomeAndExpenseFromPreviousMonth() {

        val transactions = state.value.previousMonthTransactions

        var income = 0f
        var expense = 0f

        val transactionsExpense = transactions
            .filter { it.transactionType == Transaction.TYPE_EXPENSE }
            .sumOf { it.value.toDouble() }.toFloat()
        val transactionsIncome = transactions
            .filter { it.transactionType == Transaction.TYPE_INCOME }
            .sumOf { it.value.toDouble() }.toFloat()

        expense += transactionsExpense
        income += transactionsIncome

        _state.value = state.value.copy(
            previousMonthExpense = expense,
            previousMonthIncome = income
        )
    }

    private suspend fun getTransactionsFromCurrentMonth() {

        val firstDayOfMonth = analysisUseCases.getFirstDayOfTheMonthInMillis()

        val transactions = analysisUseCases.getTransactions(firstDayOfMonth)

        _state.value = state.value.copy(currentMonthTransactions = transactions)
    }

    private suspend fun getTransactionsFromPreviousMonth() {

        val firstDayOfPreviousMonth = analysisUseCases.getFirstDayOfThePreviousMonthInMillis()
        val firstDayOfMonth = analysisUseCases.getFirstDayOfTheMonthInMillis()

        val transactions = analysisUseCases.getTransactionsFromPreviousMonth(firstDayOfPreviousMonth, firstDayOfMonth)

        _state.value = state.value.copy(previousMonthTransactions = transactions)
    }

    private suspend fun getMostExpenseByCategoryFromCurrentMonth() {

        val listOfCategoryExpenses = mutableListOf<CategoryGroupItem>()

        val firstDayOfMonth = analysisUseCases.getFirstDayOfTheMonthInMillis()

        analysisUseCases.getAllTransactionsGroupedByCategoryFromCurrentMonth(
            firstDayOfMonth, Transaction.TYPE_EXPENSE
        ).groupBy { categoryGroupItem -> categoryGroupItem.category }
            .forEach { (transactionCategory, listOfCategoryGroupItems) ->
                val item = CategoryGroupItem(
                    category = transactionCategory,
                    value = listOfCategoryGroupItems.sumOf { it.value.toDouble() }.toFloat()
                )
                listOfCategoryExpenses.add(item)
            }
        listOfCategoryExpenses.sortBy { it.value }

        if (listOfCategoryExpenses.isNotEmpty()) {
            _state.value = state.value.copy(currentMonthMostExpenseCategory = listOfCategoryExpenses.last())
        }
    }

    private suspend fun getMostIncomeByCategoryFromCurrentMonth() {

        val listOfCategoryIncomes = mutableListOf<CategoryGroupItem>()

        val firstDayOfMonth = analysisUseCases.getFirstDayOfTheMonthInMillis()

        analysisUseCases.getAllTransactionsGroupedByCategoryFromCurrentMonth(
            firstDayOfMonth, Transaction.TYPE_INCOME
        ).groupBy { categoryGroupItem -> categoryGroupItem.category }
            .forEach { (transactionCategory, listOfCategoryGroupItems) ->
                val item = CategoryGroupItem(
                    category = transactionCategory,
                    value = listOfCategoryGroupItems.sumOf { it.value.toDouble() }.toFloat()
                )
                listOfCategoryIncomes.add(item)
            }
        listOfCategoryIncomes.sortBy { it.value }

        if (listOfCategoryIncomes.isNotEmpty()) {
            _state.value = state.value.copy(currentMonthMostIncomeCategory = listOfCategoryIncomes.last())
        }
    }
}