package com.example.financeanalyzer.feature_finance.domain.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class CategoryGroupItem(
    val categoryId: Int,
    val name: String,
    val color: Color,
    @DrawableRes
    val icon: Int,
    val value: Float
)
