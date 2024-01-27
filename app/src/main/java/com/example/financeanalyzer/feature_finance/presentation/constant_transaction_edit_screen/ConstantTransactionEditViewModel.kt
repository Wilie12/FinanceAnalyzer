package com.example.financeanalyzer.feature_finance.presentation.constant_transaction_edit_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeanalyzer.feature_finance.domain.use_case.constant_transaction_edit.ConstantTransactionEditUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class ConstantTransactionEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val constantTransactionEditUseCases: ConstantTransactionEditUseCases
): ViewModel() {

    private val _state = mutableStateOf(ConstantTransactionEditState())
    val state: State<ConstantTransactionEditState> = _state

    init {
        getConstantTransaction(checkNotNull(savedStateHandle["id"]))
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
    fun setStateTransactionType(transactionType: Int) {
        _state.value = state.value.copy(transactionType = transactionType)
    }

    private fun getConstantTransaction(id: Int) {
        viewModelScope.launch {
            val constantTransaction = constantTransactionEditUseCases.getConstantTransaction(id)
            _state.value = state.value.copy(
                id = constantTransaction.id,
                name = constantTransaction.name,
                transactionType = constantTransaction.transactionType,
                value = constantTransaction.value.toString()
            )
        }
    }

    fun updateConstantTransaction() {
        viewModelScope.launch {
            constantTransactionEditUseCases.updateConstantTransaction(
                id = state.value.id,
                name = state.value.name,
                value = state.value.value.toFloat(),
                transactionType = state.value.transactionType
            )
        }
    }
}