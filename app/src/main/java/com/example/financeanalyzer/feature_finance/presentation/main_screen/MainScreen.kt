package com.example.financeanalyzer.feature_finance.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeanalyzer.feature_finance.presentation.main_screen.components.TransactionItem

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {

    val listOfTransactions by remember { viewModel.listOfTransactionsState }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.LightGray
            )
            .padding(16.dp)
    ) {
        Button(
            onClick = { viewModel.getTransactions() }
        ) {
            Text(
                text = "Get Transactions"
            )
        }
        Button(
            onClick = { viewModel.addTransaction() }
        ) {
            Text(
                text = "Add Transaction"
            )
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(listOfTransactions) { transaction ->
                TransactionItem(transaction = transaction)
            }
        }
    }
}