package com.example.financeanalyzer.feature_finance.presentation.constant_transaction_edit_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.presentation.common.FinanceTopBar
import com.example.financeanalyzer.feature_finance.presentation.util.Screen

@Composable
fun ConstantTransactionEditScreen(
    navController: NavController,
    viewModel: ConstantTransactionEditViewModel = hiltViewModel()
) {

    val selectedTransactionType = viewModel.state.value.transactionType

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            FinanceTopBar(
                navController = navController,
                title = "Edytuj"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFF87CEEB),
                        shape = RoundedCornerShape(32.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = "Rodzaj transakcji",
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(32.dp)
                        )
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Wydatek",
                        fontSize = 20.sp,
                        maxLines = 1,
                        color = if (selectedTransactionType == Transaction.TYPE_EXPENSE) Color.White else Color.LightGray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                color = if (selectedTransactionType == Transaction.TYPE_EXPENSE) Color(
                                    0xFF98FB98
                                ) else Color.White,
                                shape = RoundedCornerShape(32.dp)
                            )
                            .clip(RoundedCornerShape(32.dp))
                            .clickable { viewModel.setStateTransactionType(Transaction.TYPE_EXPENSE) }
                            .padding(8.dp)
                    )
                    Text(
                        text = "Przychód",
                        fontSize = 20.sp,
                        maxLines = 1,
                        color = if (selectedTransactionType == Transaction.TYPE_INCOME) Color.White else Color.LightGray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                color = if (selectedTransactionType == Transaction.TYPE_INCOME) Color(
                                    0xFF98FB98
                                ) else Color.White,
                                shape = RoundedCornerShape(32.dp)
                            )
                            .clip(RoundedCornerShape(32.dp))
                            .clickable { viewModel.setStateTransactionType(Transaction.TYPE_INCOME) }
                            .padding(8.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                Text(
                    text = "Szczegóły",
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = viewModel.state.value.name,
                    onValueChange = { viewModel.setStateName(it) },
                    shape = RoundedCornerShape(32.dp),
                    singleLine = true,
                    label = { Text(text = "Nazwa") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF87CEEB),
                        unfocusedBorderColor = Color(0xFF6082B6),
                        disabledBorderColor = Color.LightGray,
                        textColor = Color.Black,
                        focusedLabelColor = Color(0xFF87CEEB),
                        unfocusedLabelColor = Color(0xFF6082B6),
                        cursorColor = Color(0xFF87CEEB)
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = viewModel.state.value.value,
                    onValueChange = { viewModel.setStateValue(it) },
                    shape = RoundedCornerShape(32.dp),
                    singleLine = true,
                    label = { Text(text = "Wartość") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF87CEEB),
                        unfocusedBorderColor = Color(0xFF6082B6),
                        disabledBorderColor = Color.LightGray,
                        textColor = Color.Black,
                        focusedLabelColor = Color(0xFF87CEEB),
                        unfocusedLabelColor = Color(0xFF6082B6),
                        cursorColor = Color(0xFF87CEEB)
                    )
                )
            }
        }
        Text(
            text = "Zapisz",
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    color = Color(0xFF6082B6),
                    shape = RoundedCornerShape(32.dp)
                )
                .clip(RoundedCornerShape(32.dp))
                .clickable {
                    if (viewModel.state.value.name.isNotEmpty() && viewModel.state.value.value.isNotEmpty()) {
                        viewModel.updateConstantTransaction()
                        navController.navigate(Screen.MainScreen.route) {
                            popUpTo(Screen.MainScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                }
                .padding(8.dp)
        )
    }
}