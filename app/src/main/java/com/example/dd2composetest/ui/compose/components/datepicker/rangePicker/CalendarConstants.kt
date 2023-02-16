package com.example.dd2composetest.ui.compose.components.datepicker.rangePicker

class CalendarConstants {

    companion object {
        /**
         * 00:00:00 UTC (Gregorian) Julian day 0,
         * 0 milliseconds since *1970-01-01*
         */
        const val MILLIS_JULIAN_EPOCH = -210866803200000L

        /**
         * Milliseconds of a day calculated by
         *
         * ``24L(hours) * 60L(minutes) * 60L(seconds) * 1000L(millis)``
         */
        const val MILLIS_OF_A_DAY = 86400000L

        /**
         * The JDN of 1 Farvardin 1; Equivalent to March 19, 622 A.D.
         */
        const val PERSIAN_EPOCH = 1948321L

        /**
         * List of month names
         */
        val MONTH_NAMES = arrayOf(
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        )

        /**
         * List of persian week day names
         */
        val CHINESE_WEEK_DAY_NAMES = arrayOf(
            "日", "一", "二", "三", "四", "五", "六"
        )
    }

}