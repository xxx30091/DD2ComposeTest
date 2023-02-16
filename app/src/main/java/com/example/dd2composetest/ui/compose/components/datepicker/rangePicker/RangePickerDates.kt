package com.example.dd2composetest.ui.compose.components.datepicker.rangePicker

import java.util.*

/**
 * Utility class for common operations on timezones, calendars, date formats, and longs representing
 * time in milliseconds according to [RangePickerCalendar].
 */
class RangePickerDates {

    companion object {
        private val timeZone: TimeZone = TimeZone.getTimeZone("Asia/Taipei")

        /**
         * Returns a [RangePickerCalendar] object in IRST time zone representing the first moment of current date.
         */
        fun getTodayCalendar() = RangePickerCalendar().also {
            it.set(Calendar.HOUR_OF_DAY, 0)
            it.set(Calendar.MINUTE, 0)
            it.set(Calendar.SECOND, 0)
            it.set(Calendar.MILLISECOND, 0)
            it.timeZone = timeZone
        }

        /**
         * Returns a [RangePickerCalendar] object in IRST time zone representing the moment in input [RangePickerCalendar] object.
         * An empty [Calendar] object in IRST will be return if input is null.
         *
         * @param rawCalendar the Calendar object representing the moment to process.
         * @return A [RangePickerCalendar] object in IRST time zone.
         */
        fun getCalendarOf(rawCalendar: RangePickerCalendar?): RangePickerCalendar {
            val calendar = RangePickerCalendar()

            if (rawCalendar == null) {
                calendar[Calendar.HOUR_OF_DAY] = 0
                calendar[Calendar.MINUTE] = 0
                calendar[Calendar.SECOND] = 0
                calendar[Calendar.MILLISECOND] = 0
            } else {
                calendar.timeInMillis = rawCalendar.timeInMillis
            }
            return calendar
        }

        /**
         * Returns an empty [RangePickerCalendar] in IRST time zone.
         */
        fun getCalendar(): RangePickerCalendar = getCalendarOf(null)

        fun getCalendar(year: Int, month: Int, day: Int): RangePickerCalendar {
            val calendar = RangePickerCalendar().apply {
                setCalendarDate(
                    year = year,
                    month = month,
                    day = day
                )
            }

            return getCalendarOf(calendar)
        }

        /**
         * Returns a [RangePickerCalendar] object in IRST time zone representing the start of day in IRST represented in
         * the input [RangePickerCalendar] object, i.e., the time (fields smaller than a day) is stripped based on the
         * IRST time zone.
         *
         * @param rawCalendar the [RangePickerCalendar] object representing the moment to process.
         * @return A [RangePickerCalendar] object representing the start of day in IRST time zone.
         */
        private fun getDayCopy(rawCalendar: RangePickerCalendar?): Calendar {
            val rawCalendarInIrst = getCalendarOf(rawCalendar)
            val calendar = getCalendar()
            calendar[rawCalendarInIrst[Calendar.YEAR], rawCalendarInIrst[Calendar.MONTH]] =
                rawCalendarInIrst[Calendar.DAY_OF_MONTH]
            return calendar
        }

        /**
         * Strips all information from the time in milliseconds at granularities more specific than day of
         * the month.
         *
         * @param rawDate A long representing the time as IRST milliseconds from the epoch
         * @return A canonical long representing the time as IRST milliseconds for the represented day.
         */
        fun canonicalYearMonthDay(rawDate: Long): Long {
            val rawCalendar = getCalendar()
            rawCalendar.timeInMillis = rawDate
            val sanitizedStartItem = getDayCopy(rawCalendar)
            return sanitizedStartItem.timeInMillis
        }
    }


}