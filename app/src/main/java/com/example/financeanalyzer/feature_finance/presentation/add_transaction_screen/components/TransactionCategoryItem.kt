package com.example.financeanalyzer.feature_finance.presentation.add_transaction_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.financeanalyzer.feature_finance.domain.model.TransactionCategory

@Composable
fun TransactionCategoryItem(
    transactionCategory: TransactionCategory,
    isSelected: Boolean = false,
    onClick: (Int) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .aspectRatio(1f)
            .background(
                color = if (isSelected) transactionCategory.color else Color.White,
                shape = RoundedCornerShape(32.dp)
            )
            .border(
                width = 2.dp,
                color = transactionCategory.color,
                shape = RoundedCornerShape(32.dp)
            )
            .clip(RoundedCornerShape(32.dp))
            .clickable { onClick(transactionCategory.id) }
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(id = transactionCategory.icon),
            contentDescription = transactionCategory.name,
            tint = if (isSelected) Color.White else transactionCategory.color,
            modifier = Modifier.size(40.dp)
        )
    }
}