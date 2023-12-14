package com.example.financeanalyzer.feature_finance.presentation.main_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.math.RoundingMode

@Composable
fun TextWithValue(
    text: String,
    value: Float,
    isPositive: Boolean
) {
    Column {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp
        )
        Text(
            text = "${if (!isPositive) "-" else ""}${
                value.toBigDecimal().setScale(2, RoundingMode.HALF_DOWN)
            }zł",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}