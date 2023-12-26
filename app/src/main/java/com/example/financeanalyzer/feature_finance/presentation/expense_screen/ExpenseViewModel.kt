package com.example.financeanalyzer.feature_finance.presentation.expense_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeanalyzer.feature_finance.domain.model.CategoryGroupItem
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.use_case.ExpenseUseCases
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

    private fun getFirstDayOfTheMonthInMillis(): Long {
        val c = Calendar.getInstance()
        c.set(Calendar.HOUR_OF_DAY, 0)
        c.clear(Calendar.MINUTE)
        c.clear(Calendar.SECOND)
        c.clear(Calendar.MILLISECOND)

        c.set(Calendar.DAY_OF_MONTH, 1)

        return c.timeInMillis
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

        var normalExpense = 0f
        var constantExpense = 0f

        listOfCategoryExpenses
            .forEach { categoryGroupItem ->
                normalExpense += categoryGroupItem.value
            }
        constantTransactions
            .forEach { constantTransaction ->
                when (constantTransaction.transactionType) {
                    Transaction.TYPE_EXPENSE -> constantExpense += constantTransaction.value
                }
            }

        _state.value = state.value.copy(
            normalExpense = normalExpense,
            constantExpense = constantExpense
        )

    }

    private suspend fun getExpensesByCategoryFromCurrentMonth() {

        val listOfCategoryExpenses = mutableListOf<CategoryGroupItem>()

        val firstDayOfMonth = getFirstDayOfTheMonthInMillis()

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