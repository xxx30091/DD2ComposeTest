package com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Surface
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
import androidx.compose.ui.semantics.*
import androidx.compose.ui.text.AnnotatedString
import com.example.dd2composetest.ui.compose.components.datepicker.material3.toLocalString
import com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.DateRangePickerColors
import com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.DateRangePickerDefaults

/**
 * A layout to show a day
 *
 * @author Alireza Milani
 * @since 1.0.0
 */

/**
 * no need to pass the isRtl & dateType info, the state of the background should be controlled
 * with a single parameter
 * @author Arthur Tang
 * @since 2023.03.24
 */
@Composable
fun CalendarDay(
    date: Int,
    selected: Boolean,
    dayType: DayType,
    isRtl: Boolean,
//    bgState: DrawCalendarDay,
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
            text = date.toLocalString(),
            modifier = modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = rememberRipple(),
                    enabled = enabled,
                    onClick = onClick
                )
                .wrapContentSize(),
            color = if (selected) Color.White else Color.Black
        )
    }
}

@Composable
fun CalendarDay(
    modifier: Modifier,
    selected: Boolean,
    selectedType: DaySelectedType,
    onClick: () -> Unit,
    animateChecked: Boolean,
    enabled: Boolean,
    today: Boolean,
    inRange: Boolean,
    description: String,
    colors: DateRangePickerColors = DateRangePickerDefaults.colors(),
    content: @Composable () -> Unit
) {
    Surface(
        selected = selected,
        onClick = onClick,
        modifier = modifier
            .requiredSize(
                40.dp,
                40.dp
            )
            .semantics(mergeDescendants = true) {
                text = AnnotatedString(description)
                role = Role.Button
            },
        enabled = enabled,
        shape = when (selectedType) {
            DaySelectedType.START -> {
                RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp)
            }
            DaySelectedType.END -> {
                RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp)
            }
            DaySelectedType.UNSELECTED -> {
                RoundedCornerShape(20.dp)
            }
        },
//        shape = CircleShape,
//        shape = DatePickerModalTokens.DateContainerShape.toShape(),
        color = colors.dayContainerColor(
            selected = selected,
            enabled = enabled,
            animate = animateChecked
        ).value,
        contentColor = colors.dayContentColor(
            isToday = today,
            selected = selected,
            inRange = inRange,
            enabled = enabled
        ).value,
        border = if (today && !selected) {
            BorderStroke(
                1.dp,
                Color.Black
            )
        } else {
            null
        }
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (selected) Color.Black
                    else Color.White,
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

//@Preview
//@Composable
//fun PreviewDay() {
//    Column(modifier = Modifier.background(Color.White)) {
//        CalendarDay(
//            modifier = Modifier,
//            selected = false,
//            selectedType = DaySelectedType.UNSELECTED,
//            onClick = { /*TODO*/ },
//            animateChecked = true,
//            enabled = true,
//            today = true,
//            inRange = true,
//            description = "12313"
//        ) {
//            Text(
//                text = 28.toLocalString(),
//                // The semantics are set at the Day level.
//                modifier = Modifier.clearAndSetSemantics { },
//                textAlign = TextAlign.Center
//            )
//        }
//    }
//}

enum class DaySelectedType() {
    START,
    END,
    UNSELECTED
}

enum class DrawCalendarDay() {
    UNSELECTED_DAY,
    UNSELECTED_TODAY,
    ROUND_LEFT,
    ROUND_RIGHT,
    SELECTED_OTHER
}

@Preview
@Composable
fun CalendarDayPreview() {
    Column(modifier = Modifier.background(Color.White)) {
        CalendarDay(1, false, DayType.Today, isRtl = true)
    }
}