package com.example.financeanalyzer.feature_finance.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeanalyzer.feature_finance.domain.model.Transaction
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TransactionItem(
    transaction: Transaction
) {

    val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    val date = sdf.format(transaction.date)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .clickable {  }
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = transaction.category.color,
                        shape = RoundedCornerShape(22.dp)
                    )
                    .padding(10.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = transaction.category.icon
                    ),
                    contentDescription = transaction.category.name,
                    tint = transaction.category.color,
                    modifier = Modifier.size(30.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = transaction.category.name,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Text(
                    text = date,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
        Text(
            text = "${if (transaction.transactionType == Transaction.TYPE_EXPENSE) "-" else "+"}${transaction.value}zł",
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}