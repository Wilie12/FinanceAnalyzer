package com.example.financeanalyzer.feature_finance.presentation.util

fun parseIntToMonthString(month: Int): String {
    return when (month) {
        0 -> "Styczeń"
        1 -> "Luty"
        2 -> "Marzec"
        3 -> "Kwiecień"
        4 -> "Maj"
        5 -> "Czerwiec"
        6 -> "Lipiec"
        7 -> "Sierpień"
        8 -> "Wrzesień"
        9 -> "Październik"
        10 -> "Listopad"
        11 -> "Grudzień"
        else -> "This shouldn't happened"
    }
}