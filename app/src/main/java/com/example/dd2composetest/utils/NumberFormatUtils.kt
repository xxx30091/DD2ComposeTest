package com.example.dd2composetest.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

object NumberFormatUtils {

    fun formatNumberString(number: Int): String {
        return NumberFormat.getNumberInstance(Locale.US).format(number)
    }

}