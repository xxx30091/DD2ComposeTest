package com.example.dd2composetest.ui.compose.components.datepicker.material3


import android.os.Build
import android.text.format.DateFormat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.os.ConfigurationCompat
import java.util.Locale

/**
 * Returns a [CalendarModel] to be used by the date picker.
 */
@ExperimentalMaterial3Api
internal actual fun CalendarModel(): CalendarModel {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        CalendarModelImpl()
    } else {
        LegacyCalendarModelImpl()
    }
}

/**
 * Formats a UTC timestamp into a string with a given date format skeleton.
 *
 * A skeleton is similar to, and uses the same format characters as described in
 * [Unicode Technical Standard #35](https://unicode.org/reports/tr35/tr35-dates.html#Date_Field_Symbol_Table)
 *
 * One difference is that order is irrelevant. For example, "MMMMd" will return "MMMM d" in the
 * en_US locale, but "d. MMMM" in the de_CH locale.
 *
 * @param utcTimeMillis a UTC timestamp to format (milliseconds from epoch)
 * @param skeleton a date format skeleton
 * @param locale the [Locale] to use when formatting the given timestamp
 */
@ExperimentalMaterial3Api
internal actual fun formatWithSkeleton(
    utcTimeMillis: Long,
    skeleton: String,
    locale: Locale
): String {
    val pattern = DateFormat.getBestDateTimePattern(locale, skeleton)
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        CalendarModelImpl.formatWithPattern(utcTimeMillis, pattern, locale)
    } else {
        LegacyCalendarModelImpl.formatWithPattern(utcTimeMillis, pattern, locale)
    }
}

/**
 * A composable function that returns the default [Locale]. It will be recomposed when the
 * `Configuration` gets updated.
 */
@Composable
@ReadOnlyComposable
@ExperimentalMaterial3Api
internal actual fun defaultLocale(): Locale {
    return ConfigurationCompat.getLocales(LocalConfiguration.current).get(0) ?: Locale.getDefault()
}