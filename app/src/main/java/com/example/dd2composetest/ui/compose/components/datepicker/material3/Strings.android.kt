package com.example.dd2composetest.ui.compose.components.datepicker.material3


import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.R
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.ConfigurationCompat
import java.util.Locale

@Composable
@ReadOnlyComposable
internal actual fun getString(string: Strings): String {
    LocalConfiguration.current
    val resources = LocalContext.current.resources
    return when (string) {
        Strings.NavigationMenu -> resources.getString(R.string.navigation_menu)
        Strings.CloseDrawer -> resources.getString(R.string.close_drawer)
        Strings.CloseSheet -> resources.getString(R.string.close_sheet)
        Strings.DefaultErrorMessage -> resources.getString(R.string.default_error_message)
        Strings.ExposedDropdownMenu -> resources.getString(R.string.dropdown_menu)
        Strings.SliderRangeStart -> resources.getString(R.string.range_start)
        Strings.SliderRangeEnd -> resources.getString(R.string.range_end)
        Strings.Dialog -> resources.getString(com.example.dd2composetest.R.string.dialog)
        Strings.MenuExpanded -> resources.getString(com.example.dd2composetest.R.string.expanded)
        Strings.MenuCollapsed -> resources.getString(com.example.dd2composetest.R.string.collapsed)
        Strings.SnackbarDismiss -> resources.getString(
//            com.example.dd2composetest.R.string.snackbar_dismiss
            com.example.dd2composetest.R.string.snackbar_dismiss
        )

        Strings.SearchBarSearch -> resources.getString(
            com.example.dd2composetest.R.string.search_bar_search
        )

        Strings.SuggestionsAvailable ->
            resources.getString(com.example.dd2composetest.R.string.suggestions_available)

        Strings.DatePickerTitle -> resources.getString(
            com.example.dd2composetest.R.string.date_picker_title
        )

        Strings.DatePickerHeadline -> resources.getString(
            com.example.dd2composetest.R.string.date_picker_headline
        )

        Strings.DatePickerYearPickerPaneTitle -> resources.getString(
            com.example.dd2composetest.R.string.date_picker_year_picker_pane_title
        )

        Strings.DatePickerSwitchToYearSelection -> resources.getString(
            com.example.dd2composetest.R.string.date_picker_switch_to_year_selection
        )

        Strings.DatePickerSwitchToDaySelection -> resources.getString(
            com.example.dd2composetest.R.string.date_picker_switch_to_day_selection
        )

        Strings.DatePickerSwitchToNextMonth -> resources.getString(
            com.example.dd2composetest.R.string.date_picker_switch_to_next_month
        )

        Strings.DatePickerSwitchToPreviousMonth -> resources.getString(
            com.example.dd2composetest.R.string.date_picker_switch_to_previous_month
        )

        Strings.DatePickerNavigateToYearDescription -> resources.getString(
            com.example.dd2composetest.R.string.date_picker_navigate_to_year_description
        )

        Strings.DatePickerHeadlineDescription -> resources.getString(
            com.example.dd2composetest.R.string.date_picker_headline_description
        )

        Strings.DatePickerNoSelectionDescription -> resources.getString(
            com.example.dd2composetest.R.string.date_picker_no_selection_description
        )
        Strings.DatePickerTodayDescription -> resources.getString(
            com.example.dd2composetest.R.string.date_picker_today_description
        )
        Strings.DatePickerScrollToShowLaterYears -> resources.getString(
            com.example.dd2composetest.R.string.date_picker_scroll_to_later_years
        )
        Strings.DatePickerScrollToShowEarlierYears -> resources.getString(
            com.example.dd2composetest.R.string.date_picker_scroll_to_earlier_years
        )
        Strings.DateInputTitle -> resources.getString(
            com.example.dd2composetest.R.string.date_input_title
        )
        Strings.DateInputHeadline -> resources.getString(
            com.example.dd2composetest.R.string.date_input_headline
        )
        Strings.DateInputLabel -> resources.getString(
            com.example.dd2composetest.R.string.date_input_label
        )
        Strings.DateInputHeadlineDescription -> resources.getString(
            com.example.dd2composetest.R.string.date_input_headline_description
        )
        Strings.DateInputNoInputDescription -> resources.getString(
            com.example.dd2composetest.R.string.date_input_no_input_description
        )
        Strings.DateInputInvalidNotAllowed -> resources.getString(
            com.example.dd2composetest.R.string.date_input_invalid_not_allowed
        )
        Strings.DateInputInvalidForPattern -> resources.getString(
            com.example.dd2composetest.R.string.date_input_invalid_for_pattern
        )
        Strings.DateInputInvalidYearRange -> resources.getString(
            com.example.dd2composetest.R.string.date_input_invalid_year_range
        )
        Strings.DatePickerSwitchToCalendarMode -> resources.getString(
            com.example.dd2composetest.R.string.date_picker_switch_to_calendar_mode
        )
        Strings.DatePickerSwitchToInputMode -> resources.getString(
            com.example.dd2composetest.R.string.date_picker_switch_to_input_mode
        )
        Strings.DateRangePickerTitle -> resources.getString(
            com.example.dd2composetest.R.string.date_range_picker_title
        )
        Strings.DateRangePickerStartHeadline -> resources.getString(
            com.example.dd2composetest.R.string.date_range_picker_start_headline
        )
        Strings.DateRangePickerEndHeadline -> resources.getString(
            com.example.dd2composetest.R.string.date_range_picker_end_headline
        )
        Strings.DateRangePickerScrollToShowNextMonth -> resources.getString(
            com.example.dd2composetest.R.string.date_range_picker_scroll_to_next_month
        )
        Strings.DateRangePickerScrollToShowPreviousMonth -> resources.getString(
            com.example.dd2composetest.R.string.date_range_picker_scroll_to_previous_month
        )
        Strings.DateRangePickerDayInRange -> resources.getString(
            com.example.dd2composetest.R.string.date_range_picker_day_in_range
        )
        Strings.DateRangeInputTitle -> resources.getString(
            com.example.dd2composetest.R.string.date_range_input_title
        )
        Strings.DateRangeInputInvalidRangeInput -> resources.getString(
            com.example.dd2composetest.R.string.date_range_input_invalid_range_input
        )
        Strings.BottomSheetDragHandleDescription -> resources.getString(
            com.example.dd2composetest.R.string.bottom_sheet_drag_handle_description
        )
        Strings.BottomSheetPartialExpandDescription -> resources.getString(
            com.example.dd2composetest.R.string.bottom_sheet_collapse_description
        )
        Strings.BottomSheetDismissDescription -> resources.getString(
            com.example.dd2composetest.R.string.bottom_sheet_dismiss_description
        )
        Strings.BottomSheetExpandDescription -> resources.getString(
            com.example.dd2composetest.R.string.bottom_sheet_expand_description
        )
        Strings.TooltipLongPressLabel -> resources.getString(
            com.example.dd2composetest.R.string.tooltip_long_press_label
        )
        Strings.TimePickerAM -> resources.getString(
            com.example.dd2composetest.R.string.time_picker_am
        )
        Strings.TimePickerPM -> resources.getString(
            com.example.dd2composetest.R.string.time_picker_pm
        )
        Strings.TimePickerPeriodToggle -> resources.getString(
            com.example.dd2composetest.R.string.time_picker_period_toggle_description
        )
        Strings.TimePickerMinuteSelection -> resources.getString(
            com.example.dd2composetest.R.string.time_picker_minute_selection
        )
        Strings.TimePickerHourSelection -> resources.getString(
            com.example.dd2composetest.R.string.time_picker_hour_selection
        )
        Strings.TimePickerHourSuffix -> resources.getString(
            com.example.dd2composetest.R.string.time_picker_hour_suffix
        )
        Strings.TimePickerMinuteSuffix -> resources.getString(
            com.example.dd2composetest.R.string.time_picker_minute_suffix
        )
        Strings.TimePicker24HourSuffix -> resources.getString(
            com.example.dd2composetest.R.string.time_picker_hour_24h_suffix
        )
        Strings.TimePickerHour -> resources.getString(
            com.example.dd2composetest.R.string.time_picker_hour
        )
        Strings.TimePickerMinute -> resources.getString(
            com.example.dd2composetest.R.string.time_picker_minute
        )
        Strings.TimePickerHourTextField -> resources.getString(
            com.example.dd2composetest.R.string.time_picker_hour_text_field
        )
        Strings.TimePickerMinuteTextField -> resources.getString(
            com.example.dd2composetest.R.string.time_picker_minute_text_field
        )
        Strings.TooltipPaneDescription -> resources.getString(
            com.example.dd2composetest.R.string.tooltip_pane_description
        )
        else -> ""
    }
}
@Composable
@ReadOnlyComposable
internal actual fun getString(string: Strings, vararg formatArgs: Any): String {
    val raw = getString(string)
    val locale =
        ConfigurationCompat.getLocales(LocalConfiguration.current).get(0) ?: Locale.getDefault()
    return String.format(locale, raw, *formatArgs)
}