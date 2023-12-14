package com.example.financeanalyzer.feature_finance.presentation.expense_screen

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.financeanalyzer.R
import com.example.financeanalyzer.feature_finance.data.util.Constants
import com.example.financeanalyzer.feature_finance.domain.model.CategoryGroupItem
import com.example.financeanalyzer.feature_finance.presentation.main_screen.components.TextWithValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpenseScreen(
    navController: NavController
) {

    val list = listOf(
        CategoryGroupItem(
            categoryId = 0,
            name = Constants.transactionCategories[0].name,
            color = Constants.transactionCategories[0].color,
            icon = Constants.transactionCategories[0].icon,
            value = 3213.33f
        ),
        CategoryGroupItem(
            categoryId = 1,
            name = Constants.transactionCategories[1].name,
            color = Constants.transactionCategories[1].color,
            icon = Constants.transactionCategories[1].icon,
            value = 2213.33f
        ),
        CategoryGroupItem(
            categoryId = 2,
            name = Constants.transactionCategories[2].name,
            color = Constants.transactionCategories[2].color,
            icon = Constants.transactionCategories[2].icon,
            value = 1413.33f
        ),
    )

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
                    text = "Miesiąc",
                    fontSize = 20.sp,
                    color = Color.White
                )
                Text(
                    text = "-777777.77zł",
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
                        value = 3542.62f,
                        isPositive = false
                    )
                    TextWithValue(
                        text = "Zwykłe",
                        value = 7321.32f,
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
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(list) {
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