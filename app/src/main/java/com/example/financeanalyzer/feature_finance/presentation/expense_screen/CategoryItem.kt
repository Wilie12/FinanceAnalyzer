package com.example.financeanalyzer.feature_finance.presentation.expense_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeanalyzer.feature_finance.domain.model.CategoryGroupItem
import com.example.financeanalyzer.R

@Composable
fun CategoryItem(
    categoryGroupItem: CategoryGroupItem
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = categoryGroupItem.color,
                shape = RoundedCornerShape(32.dp)
            )
            .clip(RoundedCornerShape(32.dp))
            .clickable {  }
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = categoryGroupItem.name,
                fontSize = 18.sp,
                color = Color.Black
            )
            Icon(
                painter = painterResource(id = categoryGroupItem.icon),
                contentDescription = categoryGroupItem.name,
                tint = categoryGroupItem.color,
                modifier = Modifier.size(30.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "${categoryGroupItem.value}zł",
                fontSize = 22.sp,
                color = Color.Black
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Details",
                tint = Color.Black,
                modifier = Modifier
                    .size(30.dp)
                    .rotate(180f)
                    .clip(CircleShape)

            )
        }
    }
}