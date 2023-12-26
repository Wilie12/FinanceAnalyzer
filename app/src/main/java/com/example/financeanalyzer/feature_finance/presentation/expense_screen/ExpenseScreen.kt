package com.example.financeanalyzer.feature_finance.presentation.expense_screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeanalyzer.R
import com.example.financeanalyzer.feature_finance.presentation.expense_screen.components.CategoryItem
import com.example.financeanalyzer.feature_finance.presentation.main_screen.components.TextWithValue
import java.math.RoundingMode

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpenseScreen(
    navController: NavController,
    viewModel: ExpenseViewModel = hiltViewModel()
) {

    var animationPlayed by remember { mutableStateOf(false) }
    val animatedExpense by animateFloatAsState(
        targetValue = if (animationPlayed) {
             (viewModel.state.value.normalExpense + viewModel.state.value.constantExpense)
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .clickable {
                            navController.navigateUp()
                        }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Wydatki",
                    fontSize = 22.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFF87CEEB),
//                        color = Color(0xFF6082B6),
                        shape = RoundedCornerShape(32.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = viewModel.currentMonth.value,
                    fontSize = 20.sp,
                    color = Color.White
                )
                Text(
                    text = "-${animatedExpense.toBigDecimal().setScale(2, RoundingMode.HALF_DOWN)}zł",
                    fontSize = 40.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
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
                        value = viewModel.state.value.constantExpense,
                        isPositive = false
                    )
                    TextWithValue(
                        text = "Zwykłe",
                        value = viewModel.state.value.normalExpense,
                        isPositive = false
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
                cells = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = LinearEasing
                        )
                    )
                    .fillMaxHeight(if (animationPlayed) 1f else 0f)
            ) {
                items(viewModel.state.value.categoryGroupItems) {
                    CategoryItem(
                        categoryGroupItem = it
                    )
                }
            }
        }
        Text(
            text = "Dodaj wydatek",
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
                .clickable { }
                .padding(8.dp)

        )
    }
}