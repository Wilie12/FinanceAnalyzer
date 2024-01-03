package com.example.financeanalyzer.feature_finance.presentation.normal_category_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeanalyzer.feature_finance.data.util.Constants
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.model.TransactionCategory
import com.example.financeanalyzer.feature_finance.domain.use_case.normal_category.NormalCategoryUseCases
import com.example.financeanalyzer.feature_finance.presentation.util.parseIntToMonthString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NormalCategoryViewModel @Inject constructor(
    private val normalCategoryUseCases: NormalCategoryUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _state = mutableStateOf(NormalCategoryState())
    val state: State<NormalCategoryState> = _state

    init {
        getCurrentMonth()
        getCategory(checkNotNull(savedStateHandle["categoryId"]))
        getTransactions()
    }

    private fun getCurrentMonth() {
        val c = Calendar.getInstance()

        _state.value = state.value.copy(
            currentMonth = parseIntToMonthString(c.get(Calendar.MONTH))
        )
    }

    private fun getCategory(categoryId: Int) {
        _state.value = state.value.copy(
            category = Constants.transactionCategories[categoryId]
        )
    }

    private fun getTransactions() {

        viewModelScope.launch {

            _state.value = state.value.copy(isLoading = true)

            val job = viewModelScope.launch {
                getTransactionsExpense(
                    normalCategoryUseCases.getFirstDayOfTheMonthInMillis(),
                    state.value.category
                )
            }
            job.join()

            getTotalExpense()

            _state.value = state.value.copy(isLoading = false)
        }
    }

    private suspend fun getTransactionsExpense(
        firstDayOfMonth: Long,
        category: TransactionCategory
    ) {
        val listOfTransactions =
            normalCategoryUseCases.getAllTransactionsFromCurrentMonthByCategory(
                firstDayOfMonth,
                category
            )
        _state.value = state.value.copy(
            transactionsExpense = listOfTransactions.filter { it.transactionType == Transaction.TYPE_EXPENSE }
        )
    }

    private fun getTotalExpense() {
        val transactionsExpense = state.value.transactionsExpense

        val totalExpense = transactionsExpense.sumOf { it.value.toDouble() }.toFloat()

        _state.value = state.value.copy(
            totalExpenseOnCategory = totalExpense
        )
    }
}