package com.example.financeanalyzer.feature_finance.presentation.add_transaction_screen

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeanalyzer.R
import com.example.financeanalyzer.feature_finance.data.util.Constants
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.presentation.add_transaction_screen.components.TransactionCategoryItem
import com.example.financeanalyzer.feature_finance.presentation.common.FinanceTopBar
import com.example.financeanalyzer.feature_finance.presentation.util.Screen
import com.example.financeanalyzer.feature_finance.presentation.util.TransactionType
import java.util.*

@Composable
fun AddTransactionScreen(
    navController: NavController,
    viewModel: AddTransactionViewModel = hiltViewModel()
) {

    val selectedTransactionType = viewModel.state.value.transactionType
    val selectedType = viewModel.state.value.type
    val selectedCategoryExpense = viewModel.state.value.categoryExpense
    val selectedCategoryIncome = viewModel.state.value.categoryIncome

    val scrollState = rememberScrollState()

    val c = Calendar.getInstance()

    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, _: Int, mMonth: Int, mDayOfMonth: Int ->
            viewModel.setStateDate(mDayOfMonth, mMonth)
            viewModel.setDateText(viewModel.state.value.date)
        }, year, month, day
    )
    datePickerDialog.datePicker.minDate = viewModel.firstDayOfTheMonth
    datePickerDialog.datePicker.maxDate = viewModel.lastDayOfTheMonth

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
                title = "Dodaj ${if (selectedTransactionType == Transaction.TYPE_EXPENSE) "wydatek" else "przychód"}"
            )
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
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Typ transakcji",
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
                        text = "Normalny",
                        fontSize = 20.sp,
                        maxLines = 1,
                        color = if (selectedType == TransactionType.TYPE_NORMAL) Color.White else Color.LightGray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                color = if (selectedType == TransactionType.TYPE_NORMAL) Color(
                                    0xFF98FB98
                                ) else Color.White,
                                shape = RoundedCornerShape(32.dp)
                            )
                            .clip(RoundedCornerShape(32.dp))
                            .clickable { viewModel.setStateType(TransactionType.TYPE_NORMAL) }
                            .padding(8.dp)
                    )
                    Text(
                        text = "Stały",
                        fontSize = 20.sp,
                        maxLines = 1,
                        color = if (selectedType == TransactionType.TYPE_CONSTANT) Color.White else Color.LightGray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                color = if (selectedType == TransactionType.TYPE_CONSTANT) Color(
                                    0xFF98FB98
                                ) else Color.White,
                                shape = RoundedCornerShape(32.dp)
                            )
                            .clip(RoundedCornerShape(32.dp))
                            .clickable { viewModel.setStateType(TransactionType.TYPE_CONSTANT) }
                            .padding(8.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.verticalScroll(scrollState)) {
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
                AnimatedVisibility(visible = selectedType == TransactionType.TYPE_NORMAL) {
                    Column {
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(32.dp))
                                .clickable { datePickerDialog.show() }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_date),
                                    contentDescription = "Data",
                                    tint = Color(0xFF6082B6),
                                    modifier = Modifier.size(40.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = viewModel.dateText.value,
                                    fontSize = 18.sp
                                )
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = "Enter",
                                tint = Color(0xFF6082B6),
                                modifier = Modifier
                                    .size(40.dp)
                                    .rotate(180f)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Kategoria",
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(50.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                        ) {
                            when (selectedTransactionType) {
                                Transaction.TYPE_EXPENSE -> {
                                    items(Constants.transactionCategories.filter { it.transactionType == Transaction.TYPE_EXPENSE }) {
                                        TransactionCategoryItem(
                                            transactionCategory = it,
                                            isSelected = it.id == selectedCategoryExpense.id,
                                            onClick = { categoryId ->
                                                viewModel.setStateCategoryExpense(Constants.transactionCategories[categoryId])
                                            }
                                        )
                                    }
                                }
                                else -> {
                                    items(Constants.transactionCategories.filter { it.transactionType == Transaction.TYPE_INCOME }) {
                                        TransactionCategoryItem(
                                            transactionCategory = it,
                                            isSelected = it.id == selectedCategoryIncome.id,
                                            onClick = { categoryId ->
                                                viewModel.setStateCategoryIncome(Constants.transactionCategories[categoryId])
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Text(
            text = "Dodaj",
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
                        viewModel.addTransaction()
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