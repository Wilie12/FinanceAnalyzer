package com.example.financeanalyzer.feature_finance.presentation.main_screen.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
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

    var animationPlayed by remember { mutableStateOf(false) }

    val animatedValue by animateFloatAsState(
        targetValue = if (animationPlayed) value else 0f,
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearEasing
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Column {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp
        )
        Text(
            text = "${if (!isPositive) "-" else ""}${
                animatedValue.toBigDecimal().setScale(2, RoundingMode.HALF_DOWN)
            }zł",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}