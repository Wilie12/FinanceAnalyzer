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
            val listOfTransactions = financeRepository.getAllTransactionsFromCurrentMonth()
            _listOfTransactionsState.value = listOfTransactions
        }
    }

    fun addTransaction() {
        viewModelScope.launch {

            val transaction = Transaction(
                0,
                100L,
                Constants.transactionCategories[1],
                1210.40f,
                "Slary",
                Transaction.TYPE_INCOME
            )

            financeRepository.addTransaction(transaction)
        }
    }
}