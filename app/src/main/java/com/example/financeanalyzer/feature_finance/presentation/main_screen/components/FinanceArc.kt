package com.example.financeanalyzer.feature_finance.presentation.main_screen.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.math.RoundingMode

@Composable
fun FinanceArc(
    maxValue: Float,
    value: Float,
) {

    val percentage by remember { mutableStateOf(value / maxValue) }
    var animationPlayed by remember { mutableStateOf(false) }
    var percValue = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 1f,
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearEasing
        )
    )
    var valueAnim = animateFloatAsState(
        targetValue = if (animationPlayed) value else maxValue,
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearEasing
        )
    )

    LaunchedEffect(true) {
        animationPlayed = true
    }
    Box {
        Canvas(
            modifier = Modifier
                .size(150.dp)
                .padding(8.dp)
        ) {
            drawArc(
                color = Color.White,
                startAngle = -205f,
                sweepAngle = 230f,
                useCenter = false,
                style = Stroke(
                    width = 22.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )
            drawArc(
                color = Color(0xFF98FB98),
                startAngle = -205f,
                sweepAngle = 230f * percValue.value,
                useCenter = false,
                style = Stroke(
                    width = 12.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = "Pozostało",
                fontSize = 18.sp,
                color = Color.White
            )
            Text(
                text = "${valueAnim.value.toBigDecimal().setScale(2, RoundingMode.HALF_DOWN)}zł",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}