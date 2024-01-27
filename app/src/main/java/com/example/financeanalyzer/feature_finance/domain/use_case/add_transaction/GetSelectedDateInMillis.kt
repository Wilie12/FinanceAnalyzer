package com.example.financeanalyzer.feature_finance.domain.use_case.add_transaction

import java.util.*

class GetSelectedDateInMillis {

    operator fun invoke(day: Int, month: Int): Long {
        val c = Calendar.getInstance()
        c.set(Calendar.HOUR_OF_DAY, 0)
        c.clear(Calendar.MINUTE)
        c.clear(Calendar.SECOND)
        c.clear(Calendar.MILLISECOND)

        c.set(Calendar.DAY_OF_MONTH, day)
        c.set(Calendar.MONTH, month)

        return c.timeInMillis
    }
}