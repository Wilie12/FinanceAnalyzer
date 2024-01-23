package com.example.financeanalyzer.feature_finance.presentation.constant_transactions_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.financeanalyzer.R
import com.example.financeanalyzer.feature_finance.domain.model.ConstantTransaction
import java.math.RoundingMode

@Composable
fun ConstantTransactionItem(
    constantTransaction: ConstantTransaction,
    onClick: (Int) -> Unit
) {
    // TODO - try changing icon placement

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = constantTransaction.name,
            color = Color.Black,
            fontSize = 20.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.weight(4f)
        )
        Text(
            text = "" +
                    (if (constantTransaction.transactionType == ConstantTransaction.TYPE_EXPENSE) "-" else "+") +
                    "${constantTransaction.value.toBigDecimal().setScale(2, RoundingMode.HALF_DOWN)}zł",
            color = Color.Black,
            fontSize = 20.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(4f)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_edit),
            contentDescription = "Edit",
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .weight(2f)
                .clickable {
                    onClick(constantTransaction.id)
                }
        )
    }
}