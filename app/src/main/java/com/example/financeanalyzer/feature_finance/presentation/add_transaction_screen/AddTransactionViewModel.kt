package com.example.financeanalyzer.feature_finance.presentation.add_transaction_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.model.TransactionCategory
import com.example.financeanalyzer.feature_finance.domain.use_case.add_transaction.AddTransactionUseCases
import com.example.financeanalyzer.feature_finance.presentation.util.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val addTransactionUseCases: AddTransactionUseCases
) : ViewModel() {

    private val _state = mutableStateOf(AddTransactionState())
    val state: State<AddTransactionState> = _state

    val firstDayOfTheMonth = addTransactionUseCases.getFirstDayOfTheMonthInMillis()
    val lastDayOfTheMonth = addTransactionUseCases.getLastDayOfTheMonthInMillis()

    var dateText = mutableStateOf("")

    init {
        setCurrentDate()
        setDateText(state.value.date)
    }

    fun setStateCategoryExpense(category: TransactionCategory) {
        _state.value = state.value.copy(categoryExpense = category)
    }

    fun setStateCategoryIncome(category: TransactionCategory) {
        _state.value = state.value.copy(categoryIncome = category)
    }

    fun setStateTransactionType(transactionType: Int) {
        _state.value = state.value.copy(transactionType = transactionType)
    }

    fun setStateType(type: Int) {
        _state.value = state.value.copy(type = type)
    }

    fun setStateName(name: String) {
        _state.value = state.value.copy(name = name)
    }

    fun setStateValue(value: String) {
        try {
            _state.value = state.value.copy(
                value = value.toBigDecimal().setScale(2, RoundingMode.HALF_DOWN).toString()
            )
        } catch (_: Exception) {

        }
    }

    fun setSelectedDate(day: Int, month: Int) {
        _state.value = state.value.copy(
            date = addTransactionUseCases.getSelectedDateInMillis(day, month)
        )
    }

    fun setDateText(date: Long) {
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        dateText.value = sdf.format(date)
    }

    private fun setCurrentDate() {
        _state.value = state.value.copy(
            date = System.currentTimeMillis()
        )
    }

    fun addTransaction() {
        viewModelScope.launch {
            when (state.value.type) {
                TransactionType.TYPE_NORMAL -> {
                    addTransactionUseCases.addTransaction(
                        transactionType = state.value.transactionType,
                        name = state.value.name,
                        value = state.value.value.toFloat(),
                        date = state.value.date,
                        category = if (state.value.transactionType == Transaction.TYPE_EXPENSE) state.value.categoryExpense else state.value.categoryIncome
                    )
                }
                TransactionType.TYPE_CONSTANT -> {
                    addTransactionUseCases.addConstantTransaction(
                        transactionType = state.value.transactionType,
                        name = state.value.name,
                        value = state.value.value.toFloat()
                    )
                }
            }
        }
    }
}