package com.example.financeanalyzer.feature_finance.presentation.expense_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeanalyzer.feature_finance.domain.model.CategoryGroupItem
import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.use_case.expense.ExpenseUseCases
import com.example.financeanalyzer.feature_finance.presentation.util.parseIntToMonthString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val expenseUseCases: ExpenseUseCases
) : ViewModel() {

    private val _state = mutableStateOf(ExpenseState())
    val state: State<ExpenseState> = _state

    private var _currentMonth = mutableStateOf("")
    val currentMonth: State<String> = _currentMonth

    init {
        getCurrentMonth()
        getExpenses()
    }

    private fun getCurrentMonth() {
        val c = Calendar.getInstance()

        _currentMonth.value = parseIntToMonthString(c.get(Calendar.MONTH))
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

    private suspend fun getExpenseFromCurrentMonth() {

        val constantTransactions = expenseUseCases.getConstantTransactions()
        val listOfCategoryExpenses = state.value.categoryGroupItems

        val normalExpense = listOfCategoryExpenses.sumOf { it.value.toDouble() }.toFloat()
        val constantExpense = constantTransactions
            .filter { it.transactionType == ConstantTransaction.TYPE_EXPENSE }
            .sumOf { it.value.toDouble() }.toFloat()

        _state.value = state.value.copy(
            normalExpense = normalExpense,
            constantExpense = constantExpense
        )

    }

    private suspend fun getExpensesByCategoryFromCurrentMonth() {

        val listOfCategoryExpenses = mutableListOf<CategoryGroupItem>()

        val firstDayOfMonth = expenseUseCases.getFirstDayOfTheMonthInMillis()

        expenseUseCases.getAllTransactionsGroupedByCategoryFromCurrentMonth(
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
}