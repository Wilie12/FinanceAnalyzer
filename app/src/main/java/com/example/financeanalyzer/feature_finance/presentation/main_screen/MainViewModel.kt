package com.example.financeanalyzer.feature_finance.presentation.main_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeanalyzer.feature_finance.data.util.Constants
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val financeRepository: FinanceRepository
): ViewModel() {

    private var _listOfTransactionsState = mutableStateOf<List<Transaction>>(emptyList())
    val listOfTransactionsState: State<List<Transaction>> = _listOfTransactionsState

    init {
        getTransactions()
    }

    fun getTransactions() {
        viewModelScope.launch {

            val firstDayOfMonth = getFirstDayOfTheMonthInMillis()
            val listOfTransactions = financeRepository.getAllTransactionsFromCurrentMonth(firstDayOfMonth)
            _listOfTransactionsState.value = listOfTransactions
        }
    }

    fun addTransaction() {
        viewModelScope.launch {

            val transaction = Transaction(
                0,
                1701289500000,
                Constants.transactionCategories[0],
                77777.40f,
                "Slary",
                Transaction.TYPE_EXPENSE
            )

            financeRepository.addTransaction(transaction)
        }
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
}