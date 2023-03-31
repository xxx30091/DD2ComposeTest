package com.example.dd2composetest.ui.compose.components.datepicker.rangePicker

import android.content.res.Resources
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.dd2composetest.enum.DayType
import java.util.*
import com.example.dd2composetest.R

class DateRangePickerState(
    private val initialDates: Pair<Long?, Long?>? = null,
    yearRange: IntRange,
    private val resources: Resources
) {
    // 紀錄所選擇的起始/結束日期
    var selectedStartDate: Long? by mutableStateOf(null)
    var selectedEndDate: Long? by mutableStateOf(null)

    var position: Int by mutableStateOf(0)

    val months = mutableListOf<RangePickerCalendar>()

    init {
        if (initialDates != null) {
            setSelection(initialDates)
        }
        getMonths(yearRange)
    }

    /**
     * Creates list of months according to year range
     */
    private fun getMonths(yearRange: IntRange) {
        val calendar = if (initialDates?.first != null) {
            RangePickerDates.getCalendarOf(RangePickerCalendar(initialDates.first!!))
        } else {
            RangePickerDates.getTodayCalendar()
        }

        for (i in 0 until (yearRange.last - yearRange.first + 1) * 12) {

            // 這個有問題
            val currentCalendar = RangePickerDates.getCalendar(
                year = yearRange.first + (i / 12), month = i % 12, day = 1
            )

            if (currentCalendar.calendarYear == calendar.calendarYear && currentCalendar.calendarMonth == calendar.calendarMonth) {
                position = i
            }

            months.add(currentCalendar)
            Log.i("Arthur_debug", "i: $i")
            Log.i("Arthur_debug", "currentCalendar month: ${currentCalendar.calendarMonth}, calendar month: ${calendar.calendarMonth}")
//            Log.i("Arthur_debug", "same year: ${currentCalendar.calendarYear == calendar.calendarYear}, same month: ${currentCalendar.calendarMonth == calendar.calendarMonth}")
//            Log.i("Arthur_debug", "i: $i ,position: $position")
            Log.i("Arthur_debug", "shortDateString: ${months[i].shortDateString}")
            Log.i("Arthur_debug", "calendar status: ${yearRange.first + (i / 12)}/${i % 12}")
            Log.i("Arthur_debug", "calendar status days of month:")
            Log.i("Arthur_debug", "-------------------------------------------------------")
        }
    }

    private fun setSelection(selection: Pair<Long?, Long?>) {
        if (selection.first != null && selection.second != null) {
            require(isValidRange(selection.first!!, selection.second!!))
        }

        selectedStartDate = selection.first?.let {
            RangePickerDates.canonicalYearMonthDay(it)
        }

        selectedEndDate = selection.second?.let {
            RangePickerDates.canonicalYearMonthDay(it)
        }
    }

    private fun isValidRange(start: Long, end: Long): Boolean = start <= end

    fun select(selection: Long) {
        if (selectedStartDate == null) {
            selectedStartDate = selection
        } else if (selectedEndDate == null && isValidRange(selectedStartDate!!, selection)) {
            selectedEndDate = selection
        } else {
            selectedEndDate = null
            selectedStartDate = selection
        }
    }

    fun isSelectionComplete(): Boolean {
        return selectedStartDate != null && selectedEndDate != null && isValidRange(
            selectedStartDate!!, selectedEndDate!!
        )
    }

    private fun getSelectionDays(): Collection<Long> {
        val selections = arrayListOf<Long>()
        selectedStartDate?.let {
            selections.add(it)
        }
        selectedEndDate?.let {
            selections.add(it)
        }

        return selections
    }

    fun isSelected(date: Long): Boolean {
        for (selectionDay in getSelectionDays()) {
            if (RangePickerDates.canonicalYearMonthDay(date)
                == RangePickerDates.canonicalYearMonthDay(selectionDay)
            ) {
                return true
            }
        }
        return false
    }

    fun isInRange(date: Long): Boolean {
        if (selectedStartDate == null || selectedEndDate == null) {
            return false
        }

        if (RangePickerDates.canonicalYearMonthDay(date) > RangePickerDates.canonicalYearMonthDay(
                selectedStartDate!!
            ) && RangePickerDates.canonicalYearMonthDay(date) < RangePickerDates.canonicalYearMonthDay(
                selectedEndDate!!
            )
        ) {
            return true
        }
        return false
    }

    fun getDayType(date: Long): DayType {
        var dayType: DayType = DayType.Day

        val currentDate = RangePickerDates.canonicalYearMonthDay(date)

        if (currentDate == RangePickerDates.getTodayCalendar().timeInMillis) {
            dayType = DayType.Today
        }

        if (selectedStartDate != null && selectedEndDate != null) {
            if (RangePickerDates.canonicalYearMonthDay(selectedStartDate!!) != RangePickerDates.canonicalYearMonthDay(
                    selectedEndDate!!
                )
            ) {
                when (currentDate) {
                    RangePickerDates.canonicalYearMonthDay(selectedStartDate!!) -> dayType = DayType.Start
                    RangePickerDates.canonicalYearMonthDay(selectedEndDate!!) -> dayType = DayType.End
                }
            }
        } else if (selectedStartDate == null || selectedEndDate == null) {
            if (currentDate != RangePickerDates.getTodayCalendar().timeInMillis) {
                dayType = DayType.Day
            }
        }

        return dayType
    }

    /**
     * @return first day of [calendar] and number of days in this month
     */
    fun getDates(calendar: RangePickerCalendar): Pair<Int, Int> {
        val numDays = calendar.getMonthLength()

        val firstDay = calendar.run {
            setCommonDay(1)
            val day = calendar[Calendar.DAY_OF_WEEK]

            // When day is Saturday, the above line returns value equal 7 and this isn't be useful,
            // must be convert to 0
//            if (day == 7) 0 else day
            day - 1
        }

        return Pair(firstDay, numDays)
    }

    fun getLongName(date: RangePickerCalendar): String {
        return "${date.calendarMonthName} ${date.calendarYear}"
    }

    /**
     * Creates abbreviation of days of week
     */
    fun getDisplayNameOfDay(): List<String> = listOf(
        "日",
        "一",
        "二",
        "三",
        "四",
        "五",
        "六"
    )

    /**
     * Shows text in calendar's header according to selected start and end dates
     */
    fun updateHeader(): String {
        if (selectedStartDate == null && selectedEndDate == null) {
            return resources.getString(R.string.picker_range_header_unselected)
        }

        if (selectedEndDate == null) {
            return resources.getString(
                R.string.picker_range_header_only_start_selected,
                DateStrings.getDateString(selectedStartDate!!)
            )
        }

        if (selectedStartDate == null) {
            return resources.getString(
                R.string.picker_range_header_only_end_selected,
                DateStrings.getDateString(selectedEndDate!!)
            )
        }

        val dateRangeString = DateStrings.getDateRangeString(selectedStartDate!!, selectedEndDate!!)
        return resources.getString(
            R.string.picker_range_header_selected, dateRangeString.first, dateRangeString.second
        )
    }

    fun updateDateString(): Pair<String?, String?> {
        val start = DateStrings.getFieldDateString(selectedStartDate!!)
        val end = DateStrings.getFieldDateString(selectedEndDate!!)
        return Pair(start, end)
    }

    /**
     * @return number of week in month
     */
    fun getMaximumWeeks(firstDay: Int, numDays: Int): Int {
        return if ((firstDay == 6 && numDays >= 30) || (firstDay == 5 && numDays == 31) || (firstDay == 6 && numDays == 29)) 6
        else if (firstDay == 0 && numDays == 28) 4
        else 5
    }
}

/**
* Remembers and creates an instance of [DateRangePickerState]
*/
@Composable
fun rememberDateRangePickerState(
    initialDates: Pair<RangePickerCalendar, RangePickerCalendar>? = null,
    yearRange: IntRange,
    resources: Resources = LocalContext.current.resources
): DateRangePickerState {
    val startEndDates = initialDates?.run {
        Pair(first.timeInMillis, second.timeInMillis)
    }

    return remember {
        DateRangePickerState(startEndDates, yearRange, resources)
    }
}