package com.example.financeanalyzer.feature_finance.presentation.analysis_screen.components

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.math.RoundingMode

@Composable
fun FinanceBar(
    maxValue: Float,
    value: Float,
) {
    Log.d("TEST_BAR", value.toString())
    val percentage by remember {
        if (value > 0) mutableStateOf(value / maxValue) else mutableStateOf(0f)
    }
    Log.d("TEST_BAR", percentage.toString())
    var animationPlayed by remember { mutableStateOf(false) }
    val percValue = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 1f,
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearEasing
        )
    )
    val valueAnim = animateFloatAsState(
        targetValue = if (animationPlayed) value else maxValue,
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearEasing
        )
    )
    LaunchedEffect(true) {
        animationPlayed = true
    }
    Column() {
        BoxWithConstraints(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .padding(top = 8.dp)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                drawRoundRect(
                    color = Color.White,
                    size = Size(maxWidth.toPx(), 40f),
                    cornerRadius = CornerRadius(32f),
                    style = Stroke(
                        width = 12.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                )
                drawRoundRect(
                    color = Color(0xFF98FB98),
                    size = Size(maxWidth.toPx() * percValue.value, 40f),
                    cornerRadius = CornerRadius(32f),
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = if (value < 0f) "Brakuje " else "Pozostało "
                    + "${valueAnim.value.toBigDecimal().setScale(2, RoundingMode.HALF_DOWN)}zł",
            fontSize = 18.sp,
            color = Color.White,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
    }
}