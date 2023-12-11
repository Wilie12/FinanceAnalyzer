package com.example.financeanalyzer.feature_finance.presentation.main_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeanalyzer.feature_finance.data.util.Constants
import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.domain.repository.FinanceRepository
import com.example.financeanalyzer.feature_finance.presentation.util.parseIntToMonthString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val financeRepository: FinanceRepository
) : ViewModel() {

    private var _income = mutableStateOf(0f)
    val income: State<Float> = _income
    private var _expense = mutableStateOf(0f)
    val expense: State<Float> = _expense

    private var _listOfTransactionsFromCurrentMonthState = mutableStateOf<List<Transaction>>(emptyList())
    val listOfTransactionsFromCurrentMonthState: State<List<Transaction>> = _listOfTransactionsFromCurrentMonthState

    private var _currentMonth = mutableStateOf("")
    val currentMonth: State<String> = _currentMonth

    init {
        getTransactionsFromCurrentMonth()
    }

    private fun getTransactionsFromCurrentMonth() {
        runBlocking {

            val firstDayOfMonth = getFirstDayOfTheMonthInMillis()
            val listOfTransactions =
                financeRepository.getAllTransactionsFromCurrentMonth(firstDayOfMonth)
            val listOfConstantTransactions = financeRepository.getAllConstantTransactions()

            getIncomeAndExpenseFromCurrentMonth(listOfTransactions, listOfConstantTransactions)

            _listOfTransactionsFromCurrentMonthState.value = listOfTransactions
        }
        getCurrentMonth()
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
                Constants.transactionCategories[5],
                1324.77f,
                "Cash",
                Transaction.TYPE_INCOME
            )

            financeRepository.addTransaction(transaction)
            financeRepository.addTransaction(transaction2)
            financeRepository.addTransaction(transaction)
            financeRepository.addTransaction(transaction2)
            financeRepository.addTransaction(transaction)
            financeRepository.addTransaction(transaction2)
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

    private fun getIncomeAndExpenseFromCurrentMonth(
        transactions: List<Transaction>,
        constantTransactions: List<ConstantTransaction>
    ) {
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

        _expense.value = expense
        _income.value = income
    }
}