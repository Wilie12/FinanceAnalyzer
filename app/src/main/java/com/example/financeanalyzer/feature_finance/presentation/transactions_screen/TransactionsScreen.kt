package com.example.financeanalyzer.feature_finance.presentation.transactions_screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeanalyzer.feature_finance.data.util.Constants
import com.example.financeanalyzer.feature_finance.domain.model.CategoryGroupItem
import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import com.example.financeanalyzer.feature_finance.presentation.common.FinanceTopBar
import com.example.financeanalyzer.feature_finance.presentation.transactions_screen.components.CategoryItem
import com.example.financeanalyzer.feature_finance.presentation.main_screen.components.TextWithValue
import com.example.financeanalyzer.feature_finance.presentation.util.Screen
import com.example.financeanalyzer.feature_finance.presentation.util.TransactionType
import java.math.RoundingMode

@Composable
fun TransactionsScreen(
    navController: NavController,
    viewModel: TransactionsViewModel = hiltViewModel()
) {

    var animationPlayed by remember { mutableStateOf(false) }
    val animatedExpense by animateFloatAsState(
        targetValue = if (animationPlayed) {
            (viewModel.state.value.normalValue + viewModel.state.value.constantValue)
        } else 0f,
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearEasing
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

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
                title = if (viewModel.state.value.transactionType == Transaction.TYPE_EXPENSE) "Wydatki" else "Przychody"
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
                    text = viewModel.state.value.currentMonth,
                    fontSize = 20.sp,
                    color = Color.White
                )
                Text(
                    text = "${if (viewModel.state.value.transactionType == Transaction.TYPE_EXPENSE) "-" else ""}${
                        animatedExpense.toBigDecimal().setScale(2, RoundingMode.HALF_DOWN)
                    }zł",
                    fontSize = 40.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
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
                    TextWithValue(
                        text = "Stałe",
                        value = viewModel.state.value.constantValue,
                        isPositive = (viewModel.state.value.transactionType == Transaction.TYPE_INCOME)
                    )
                    TextWithValue(
                        text = "Zwykłe",
                        value = viewModel.state.value.normalValue,
                        isPositive = (viewModel.state.value.transactionType == Transaction.TYPE_INCOME)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Kategorie",
                color = Color.Black,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = LinearEasing
                        )
                    )
                    .fillMaxHeight(if (animationPlayed) 1f else 0f)
            ) {
                item(span = { GridItemSpan(2) }) {
                    CategoryItem(
                        categoryGroupItem = CategoryGroupItem(
                            Constants.transactionCategories.last(),
                            value = viewModel.state.value.constantValue
                        )
                    ) {
                        navController.navigate(Screen.ConstantTransactionsScreen.route + "/${viewModel.state.value.transactionType}")
                    }
                }
                items(viewModel.state.value.categoryGroupItems) {
                    CategoryItem(categoryGroupItem = it) { categoryId ->
                        navController.navigate(Screen.NormalCategoryScreen.route + "/$categoryId")
                    }
                }
            }
        }
        Text(
            text = "Dodaj ${if (viewModel.state.value.transactionType == Transaction.TYPE_EXPENSE) "wydatek" else "przychód"}",
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
                .clickable { navController.navigate(
                    Screen.AddTransactionScreen.route + "/${viewModel.state.value.transactionType}/${TransactionType.TYPE_NORMAL}"
                ) }
                .padding(8.dp)

        )
    }
}