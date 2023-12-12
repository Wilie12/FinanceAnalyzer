package com.example.financeanalyzer.feature_finance.presentation.main_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeanalyzer.feature_finance.data.util.Constants
import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.use_case.TransactionUseCases
import com.example.financeanalyzer.feature_finance.presentation.util.parseIntToMonthString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val transactionUseCases: TransactionUseCases
) : ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    private var _currentMonth = mutableStateOf("")
    val currentMonth: State<String> = _currentMonth

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

    fun addTransactionTEST() {
        viewModelScope.launch {

            val transaction = Transaction(
                0,
                1701491600000,
                Constants.transactionCategories[4],
                1324.77f,
                "Shopping",
                Transaction.TYPE_EXPENSE
            )
            val transaction2 = Transaction(
                0,
                1701791600000,
                Constants.transactionCategories[7],
                12324.77f,
                "Cash",
                Transaction.TYPE_INCOME
            )

//            financeRepository.addTransaction(transaction2)

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

    private fun getCurrentMonth() {
        val c = Calendar.getInstance()

        _currentMonth.value = parseIntToMonthString(c.get(Calendar.MONTH))
    }

    private fun getIncomeAndExpenseFromCurrentMonth() {

        val transactions = state.value.transactions
        val constantTransactions = state.value.constantTransactions

        var income = 0f
        var expense = 0f

        transactions
            .forEach { transaction ->
                when (transaction.transactionType) {
                    Transaction.TYPE_EXPENSE -> expense += transaction.value
                    Transaction.TYPE_INCOME -> income += transaction.value
                }
            }
        constantTransactions
            .forEach { constantTransaction ->
                when (constantTransaction.transactionType) {
                    Transaction.TYPE_EXPENSE -> expense += constantTransaction.value
                    Transaction.TYPE_INCOME -> income += constantTransaction.value
                }
            }

        _state.value = state.value.copy(
            expense = expense,
            income = income
        )

    }

    private suspend fun getTransactionsFromCurrentMonth() {

        val firstDayOfMonth = getFirstDayOfTheMonthInMillis()

        val transactions = transactionUseCases.getTransactions(firstDayOfMonth)

        _state.value = state.value.copy(transactions = transactions)
    }

    private suspend fun getConstantTransactions() {

        val constantTransactions = transactionUseCases.getConstantTransactions()

        _state.value = state.value.copy(constantTransactions = constantTransactions)

    }
}