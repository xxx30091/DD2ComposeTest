package com.example.dd2composetest.ui.compose.components.datepicker.rangePicker

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.dd2composetest.R
import com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.ui.CalendarMonth
import com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.ui.HeaderDate
import com.example.dd2composetest.utils.DateUtils
import java.time.LocalDateTime
import java.time.Month

/**
 * A date range picker body layout
 *
 * Date range picker allows users to select range of dates in full screen layout
 *
 * @param modifier The modifier to be applied to the [DateRangePicker].
 * @param initialDates start and end dates to be shown to the user when the [DateRangePicker] is first shown.
 * @param yearRange the range of years the user should be allowed to pick from
 * @param colors [DateRangePickerColors] that will be used to resolve the colors used for this
 * [DateRangePicker] in different states. See [DateRangePickerDefaults.colors].
 * @param title The title shown in the header
 * @param saveLabel The label shown in the confirm button
 * @param isRtl Specifies your layout is ltr or rtl
 * @param onCloseClick callback when the user close this layout
 * @param onConfirmClick callback when the user completes their input
 *
 * @author Alireza Milani
 * @since 1.0.0
 */
@Composable
fun DateRangePicker(
    modifier: Modifier = Modifier,
    initialDates: Pair<RangePickerCalendar, RangePickerCalendar>? = null,
    yearRange: IntRange = IntRange(2023, 2025),
    colors: DateRangePickerColors = DateRangePickerDefaults.colors(),
    title: String? = null,
    saveLabel: String? = null,
    isRtl: Boolean = false,
    onCloseClick: () -> Unit,
    onConfirmClick: (start: RangePickerCalendar, end: RangePickerCalendar) -> Unit
) {
    val state = rememberDateRangePickerState(initialDates, yearRange)

    val headerTitle = title ?: stringResource(id = R.string.picker_range_header_title)
    val headerSaveLabel = saveLabel ?: stringResource(id = R.string.picker_range_confirm)

    CompositionLocalProvider(
        LocalLayoutDirection provides if (isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 顯示所選日期部分
            HeaderDate(colors, state, headerSaveLabel, headerTitle, onCloseClick, onConfirmClick, )

            // 週日-週六 標題
            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier
                    .size(
                        width = (48 * 7).dp,
                        height = 48.dp
                    ),
                userScrollEnabled = false
            ) {
                state.getDisplayNameOfDay().forEach { dayName ->
                    item {
                        Box(Modifier.size(48.dp)) {
                            Text(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentSize(Alignment.Center),
                                text = dayName,
//                                style = MaterialTheme.typography.bodyMedium.copy(
//                                    color = MaterialTheme.colorScheme.onSurface
//                                )
                            )
                        }
                    }
                }
            }


            val listState = rememberLazyListState(
                initialFirstVisibleItemIndex = state.position
            )

            // 月曆部分
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.White),
                state = listState,
                contentPadding = PaddingValues(horizontal = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    items = state.months,
//                    key = { state.position },
//                    key = { it.timeInMillis },
                    contentType = { RangePickerCalendar::class }
                ) { calendar ->
                    Log.i("Arthur_test", "date_range_picker, ${state.months}")
                    Text(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .height(48.dp)
                            .paddingFromBaseline(top = 32.dp)
                            .padding(horizontal = 36.dp),
                        text = state.getLongName(calendar),
//                        style = MaterialTheme.typography.titleSmall.copy(
//                            color = MaterialTheme.colorScheme.onSurface
//                        )
                    )

                    CalendarMonth(
                        state = state,
                        isRtl = isRtl,
                        colors = colors,
                        calendar = calendar
                    )
//                    Log.i("Arthur_test", "${it.timeInMillis}")
                }
            }
        }
    }
}

@Preview
@Composable
fun DateRangePickerPreview() {
    val start = RangePickerCalendar().apply {
        setCalendarDate(2023, 1, 1)
    }
    val end = RangePickerCalendar().apply {
        setCalendarDate(2023, 1, 4)
    }

    DateRangePicker(
        initialDates = start to end,
        onCloseClick = {}
    ) { _, _ -> }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarComponent(
    startDate: String = DateUtils.getTodayDate(),
    endDate: String = DateUtils.getTodayDate(),
    dateSelectedBack: (String, String) -> Unit
) {
    // 當前年月
    var cYear by remember {
        mutableStateOf(LocalDateTime.now().year)
    }
    var cMonth by remember {
        mutableStateOf(LocalDateTime.now().month)
    }

    /**
     * 選中日期
     */
//    var selectedDate by remember {
//        mutableStateListOf(startDate, endDate)
//    }


}

/**
 * 月份組件
 * @param year Int 當前年份
 * @param month Month 當前月份
 * @param selectDay List<String> 已選中的日期
 * @param preMonth Function0<Unit> 上個月點擊事件
 * @param nextMonth Function0<Unit> 下個月點擊事件
 * @param dayClick Function1<String, Unit>
 */
@Composable
fun MonthComponent(
    year: Int,
    month: Month,
    selectDay: List<String>,
    preMonth: () -> Unit,
    nextMonth: () -> Unit,
    dayClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(vertical = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
        }
    }
}