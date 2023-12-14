package com.example.financeanalyzer.feature_finance.presentation.income_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.financeanalyzer.R

@Composable
fun IncomeScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
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
                text = "Przychody",
                fontSize = 22.sp,
                color = Color.Black
            )
        }
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Text(
                text = "Under construction",
                color = Color.Black,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
            )
        }
    }

}