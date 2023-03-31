package com.example.dd2composetest.ui.compose.components.datepicker.rangePicker

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.example.dd2composetest.ui.compose.components.datepicker.material3.token.MotionTokens

/** Represents the colors of the various elements of a date range picker in different states. */
@Stable
interface DateRangePickerColors {

    /**
     * Represents the container color for header.
     */
    val headerContainerColor: Color

    /**
     * Represents the content color for header.
     */
    val headerContentColor: Color

    /**
     * Represents the container color for dates between start and end selected dates.
     */
    val rangeDateContainerColor: Color

    /**
     * Represents the container color for this date, depending on whether it is [active].
     *
     * @param active whether the item is selected
     */
    @Composable
    fun dateContainerColor(active: Boolean): State<Color>

    @Composable
    fun dayContainerColor(
        selected: Boolean,
        enabled: Boolean,
        animate: Boolean
    ): State<Color> {
        val target = if (selected) {
            if (enabled) Color.Gray else Color.LightGray
        } else {
            Color.Transparent
        }
        return if (animate) {
            animateColorAsState(
                target,
                tween(durationMillis = MotionTokens.DurationShort2.toInt())
            )
        } else {
            rememberUpdatedState(target)
        }
    }

    @Composable
    fun dayContentColor(
        isToday: Boolean,
        selected: Boolean,
        inRange: Boolean,
        enabled: Boolean
    ): State<Color> {
        val target = when {
            selected && enabled -> Color.White
            selected && !enabled -> Color.Red
            inRange && enabled -> Color.Black
            inRange && !enabled -> Color.Yellow
            isToday -> Color.Black
            enabled -> Color.Blue
            else -> Color.DarkGray
        }

        return if (inRange) {
            rememberUpdatedState(target)
        } else {
            // Animate the content color only when the day is not in a range.
            animateColorAsState(
                target,
                tween(durationMillis = MotionTokens.DurationShort2.toInt())
            )
        }
    }

    /**
     * Represents the text color for this date, depending on whether it is [active].
     *
     * @param active whether the date is selected
     */
    @Composable
    fun dateTextColor(active: Boolean): State<Color>
}

internal class DefaultDateRangePickerColors(
    override val headerContainerColor: Color,
    override val headerContentColor: Color,
    override val rangeDateContainerColor: Color,
    private val activeDateContainerColor: Color,
    private val inactiveDateContainerColor: Color,
    private val activeDateTextColor: Color,
    private val inactiveDateTextColor: Color
) : DateRangePickerColors {

    @Composable
    override fun dateContainerColor(active: Boolean): State<Color> {
        return rememberUpdatedState(if (active) activeDateContainerColor else inactiveDateContainerColor)
    }

    @Composable
    override fun dateTextColor(active: Boolean): State<Color> {
        return rememberUpdatedState(if (active) activeDateTextColor else inactiveDateTextColor)
    }
}

object DateRangePickerDefaults {

    /**
     * Creates a [NavigationDrawerItemColors] with the provided colors according to the Material
     * specification.
     *
     * @param activeDateContainerColor the color to use for the background of the date when is active
     * @param inactiveDateContainerColor the color to use for the background of the date when is inactive
     * @param activeDateTextColor the color to use for the text label when the date is active.
     * @param inactiveDateTextColor the color to use for the text label when the date is inactive.
     * @param rangeDateContainerColor the color to use for the background of the dates are between start and end dates.
     *
     * @return the resulting [DateRangePickerColors] used for [DateRangePicker]
     */
    @Composable
    fun colors(
        activeDateContainerColor: Color = Color.Black,
        inactiveDateContainerColor: Color = Color.Transparent,
        activeDateTextColor: Color = Color.White,
        inactiveDateTextColor: Color = Color.Black,
        rangeDateContainerColor: Color = activeDateContainerColor.copy(alpha = .12f),
        headerContainerColor: Color = Color.Black,
        headerContentColor: Color = Color.White
    ): DateRangePickerColors = remember(
        headerContainerColor,
        headerContentColor,
        rangeDateContainerColor,
        activeDateContainerColor,
        inactiveDateContainerColor,
        activeDateTextColor,
        inactiveDateTextColor
    ) {
        DefaultDateRangePickerColors(
            headerContainerColor,
            headerContentColor,
            rangeDateContainerColor,
            activeDateContainerColor,
            inactiveDateContainerColor,
            activeDateTextColor,
            inactiveDateTextColor
        )
    }
}