package com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dd2composetest.enum.DayType
import androidx.compose.ui.graphics.drawscope.inset
import com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.DateRangePickerColors
import com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.DateRangePickerDefaults

/**
 * A layout to show a day
 *
 * @author Alireza Milani
 * @since 1.0.0
 */

@Composable
fun CalendarDay(
    date: Int,
    selected: Boolean,
    dayType: DayType,
    isRtl: Boolean,
    isRangeFill: Boolean = false,
    enabled: Boolean = true,
    colors: DateRangePickerColors = DateRangePickerDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit = {},
) {
    val padding = with(LocalDensity.current) { 4.dp.toPx() }
//    Log.i("Arthur_test", "date: $date")
    Box(
        modifier = Modifier
            .size(48.dp)
            .drawBehind {
                inset(vertical = padding) {
                    when {
                        dayType == DayType.Start && isRtl -> {
                            drawRect(
                                color = colors.rangeDateContainerColor,
                                topLeft = Offset(x = 0f, y = 0f),
                                size = Size(size.width / 2f, size.height)
                            )
                        }
                        dayType == DayType.Start && !isRtl -> {
                            drawRect(
                                color = colors.rangeDateContainerColor,
                                topLeft = Offset(x = size.width / 2f, y = 0f),
                            )
                        }
                        dayType == DayType.End && isRtl -> {
                            drawRect(
                                color = colors.rangeDateContainerColor,
                                topLeft = Offset(x = size.width / 2f, y = 0f),
                            )
                        }
                        dayType == DayType.End && !isRtl -> {
                            drawRect(
                                color = colors.rangeDateContainerColor,
                                topLeft = Offset(x = 0f, y = 0f),
                                size = Size(size.width / 2f, size.height)
                            )
                        }
                    }


                    if (isRangeFill) {
                        drawRect(
                            color = colors.rangeDateContainerColor, size = size
                        )
                    }
                }
            }, contentAlignment = Alignment.Center
    ) {
        var modifier = Modifier.size(40.dp)

        modifier = if (dayType == DayType.Today && !selected) {
            modifier.then(Modifier.border(1.dp, color = Color.Black, CircleShape))
        } else {
            modifier.then(
                Modifier
                    .clip(CircleShape)
                    .background(colors.dateContainerColor(selected).value)
                    .alpha(if (enabled) ContentAlpha.high else ContentAlpha.disabled)
            )
        }

        Text(
            text = date.toString(),
            modifier = modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = rememberRipple(),
                    enabled = enabled,
                    onClick = onClick
                )
                .wrapContentSize(),
            color = if (selected) Color.White else Color.Black
//            style = MaterialTheme.typography.bodyMedium.copy(
//                color = colors.dateTextColor(selected).value
//            )
        )
    }
}

@Preview
@Composable
fun CalendarDayPreview() {
    CalendarDay(1, true, DayType.Today, isRtl = true)
}