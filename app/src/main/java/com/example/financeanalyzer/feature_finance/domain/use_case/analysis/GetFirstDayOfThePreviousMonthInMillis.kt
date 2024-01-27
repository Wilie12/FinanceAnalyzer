package com.example.financeanalyzer.feature_finance.domain.use_case.analysis

import java.util.Calendar

class GetFirstDayOfThePreviousMonthInMillis {

    operator fun invoke(): Long {

        val c = Calendar.getInstance()
        c.set(Calendar.HOUR_OF_DAY, 0)
        c.clear(Calendar.MINUTE)
        c.clear(Calendar.SECOND)
        c.clear(Calendar.MILLISECOND)

        c.set(Calendar.DAY_OF_MONTH, 1)
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1)

        return c.timeInMillis
    }
}