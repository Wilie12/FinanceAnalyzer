package com.example.financeanalyzer.feature_finance.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeanalyzer.R
import com.example.financeanalyzer.feature_finance.presentation.main_screen.components.FinanceArc
import com.example.financeanalyzer.feature_finance.presentation.main_screen.components.TransactionItem
import java.math.RoundingMode

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        if (!state.isLoading) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
            ) {
                Text(
                    text = viewModel.currentMonth.value,
                    fontSize = 22.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFF87CEEB),
//                        color = Color(0xFF6082B6),
                            shape = RoundedCornerShape(32.dp)
                        )
                        .padding(16.dp)
                ) {
                    FinanceArc(
                        maxValue = state.income,
                        value = state.income - state.expense
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        color = Color.White,
                        thickness = 2.dp,
                        modifier = Modifier.clip(RoundedCornerShape(16.dp))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text(
                                text = "Wydatki",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "-${
                                    state.expense.toBigDecimal().setScale(2, RoundingMode.HALF_DOWN)
                                }zł",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Column {
                            Text(
                                text = "Przychody",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "${
                                    state.income.toBigDecimal().setScale(2, RoundingMode.HALF_DOWN)
                                }zł",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Ostatnie",
                    color = Color.Black,
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.transactions) { transaction ->
                        TransactionItem(transaction = transaction)
                    }
                    item {
                        Spacer(modifier = Modifier.height(70.dp))
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFF6082B6),
                        shape = RoundedCornerShape(32.dp)
                    )
                    .align(Alignment.BottomCenter)
                    .padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_income),
                    contentDescription = "Expenses",
                    tint = Color.White,
                    modifier = Modifier
                        .size(50.dp)
                        .rotate(180f)
                        .weight(4f)
                        .clip(RoundedCornerShape(32.dp))
                        .clickable { }
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_analize),
                    contentDescription = "Analyze",
                    tint = Color(0xFF6082B6),
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(32.dp)
                        )
                        .weight(2f)
                        .clip(RoundedCornerShape(32.dp))
                        .clickable { }
                        .padding(8.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_income),
                    contentDescription = "Incomes",
                    tint = Color.White,
                    modifier = Modifier
                        .size(50.dp)
                        .weight(4f)
                        .clip(RoundedCornerShape(32.dp))
                        .clickable { }
                )
            }
        } else {
            CircularProgressIndicator(
                color = Color(0xFF6082B6),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}