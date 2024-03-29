package com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dd2composetest.R
import com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.DateRangePickerColors
import com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.DateRangePickerDefaults
import com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.DateRangePickerState
import com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.RangePickerCalendar

/**
 * A header layout to show in top of [DateRangePicker]
 *
 * @author Alireza Milani
 * @since 1.0.0
 */
@Composable
fun HeaderDate(
    colors: DateRangePickerColors,
    state: DateRangePickerState,
    saveLabel: String,
    title: String,
    onCloseClick: () -> Unit,
    onConfirmClick: (start: RangePickerCalendar, end: RangePickerCalendar) -> Unit,
    hasSelectedDate: Boolean = true,
    isList: Boolean,
    setMode: (Boolean) -> Unit
) {
    CompositionLocalProvider(LocalContentColor provides colors.headerContentColor) {
        Column(
            modifier = Modifier
                .background(colors.headerContainerColor)
                .fillMaxWidth()
                .defaultMinSize(minHeight = 128.dp)
                .padding(
                    start = 12.dp,
                    top = 8.dp,
                    end = 12.dp,
                    bottom = 24.dp
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onCloseClick) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close calendar"
                    )
                }

                TextButton(
                    onClick = {
                        onConfirmClick(
                            RangePickerCalendar(state.selectedStartDate!!),
                            RangePickerCalendar(state.selectedEndDate!!)
                        )
                    },
                    enabled = state.isSelectionComplete(),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = LocalContentColor.current,
                        disabledContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled)
                    )
                ) {
                    Text(
                        text = saveLabel,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        color = if (hasSelectedDate) Color.White else Color.LightGray
                    )
                }
            }

            Text(
                modifier = Modifier.padding(start = 50.dp),
                text = title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )

            // 日期
            Row(
                modifier = Modifier
                    .paddingFromBaseline(36.sp)
                    .padding(start = 50.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    text = state.updateHeader(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                IconButton(
                    onClick = { setMode(!isList) },
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .then(Modifier.size(24.dp))
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isList) R.drawable.baseline_mode_edit_24
                            else R.drawable.baseline_calendar_today_24
                        ),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

val initialDates: Pair<RangePickerCalendar, RangePickerCalendar>? = null
val yearRange: IntRange = IntRange(2022, 2023)

@Preview
@Composable
fun PreviewHeaderDate() {
//    val state = rememberDateRangePickerState(initialDates, yearRange)
    HeaderDate(
        colors = DateRangePickerDefaults.colors(),
        state = DateRangePickerState(Pair(0L, 86400000L), yearRange, LocalContext.current.resources),
        saveLabel = "Save Label",
        title = "Title",
        onCloseClick = { /*TODO*/ },
        onConfirmClick = {_, _  ->},
        hasSelectedDate = false,
        isList = true,
        {}
    )
}