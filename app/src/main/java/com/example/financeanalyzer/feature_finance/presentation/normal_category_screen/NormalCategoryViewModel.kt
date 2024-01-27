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
        getTransactions(state.value.category.transactionType)
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

    private fun getTransactions(transactionType: Int) {

        viewModelScope.launch {

            _state.value = state.value.copy(isLoading = true)

            val job = viewModelScope.launch {
                if (transactionType == Transaction.TYPE_EXPENSE) {
                    getTransactionsExpense(
                        normalCategoryUseCases.getFirstDayOfTheMonthInMillis(),
                        state.value.category
                    )
                } else {
                    getTransactionsIncome(
                        normalCategoryUseCases.getFirstDayOfTheMonthInMillis(),
                        state.value.category
                    )
                }
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
            transactions = listOfTransactions.filter { it.transactionType == Transaction.TYPE_EXPENSE }
        )
    }

    private suspend fun getTransactionsIncome(
        firstDayOfMonth: Long,
        category: TransactionCategory
    ) {
        val listOfTransactions =
            normalCategoryUseCases.getAllTransactionsFromCurrentMonthByCategory(
                firstDayOfMonth,
                category
            )
        _state.value = state.value.copy(
            transactions = listOfTransactions.filter { it.transactionType == Transaction.TYPE_INCOME }
        )
    }

    private fun getTotalExpense() {
        val transactionsExpense = state.value.transactions

        val totalExpense = transactionsExpense.sumOf { it.value.toDouble() }.toFloat()

        _state.value = state.value.copy(
            totalValueOnCategory = totalExpense
        )
    }

    private fun getTotalIncome() {
        val transactions = state.value.transactions

        val totalIncome = transactions.sumOf { it.value.toDouble() }.toFloat()

        _state.value = state.value.copy(
            totalValueOnCategory = totalIncome
        )
    }
}