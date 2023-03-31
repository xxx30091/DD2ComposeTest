package com.example.dd2composetest.ui.compose.components.datepicker.material3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DateRangeInputContent(
    stateData: StateData,
    dateFormatter: DatePickerFormatter,
    dateValidator: (Long) -> Boolean,
) {
    // Obtain the DateInputFormat for the default Locale.
    val defaultLocale = defaultLocale()
    val dateInputFormat = remember(defaultLocale) {
        stateData.calendarModel.getDateInputFormat(defaultLocale)
    }
    val errorDatePattern = getString(Strings.DateInputInvalidForPattern)
    val errorDateOutOfYearRange = getString(Strings.DateInputInvalidYearRange)
    val errorInvalidNotAllowed = getString(Strings.DateInputInvalidNotAllowed)
    val errorInvalidRange = getString(Strings.DateRangeInputInvalidRangeInput)
    val dateInputValidator = remember(dateInputFormat, dateFormatter) {
        DateInputValidator(
            stateData = stateData,
            dateInputFormat = dateInputFormat,
            dateFormatter = dateFormatter,
            dateValidator = dateValidator,
            errorDatePattern = errorDatePattern,
            errorDateOutOfYearRange = errorDateOutOfYearRange,
            errorInvalidNotAllowed = errorInvalidNotAllowed,
            errorInvalidRangeInput = errorInvalidRange
        )
    }
    Row(
        modifier = Modifier.padding(paddingValues = InputTextFieldPadding),
        horizontalArrangement = Arrangement.spacedBy(TextFieldSpacing)
    ) {
        val pattern = dateInputFormat.patternWithDelimiters.uppercase()
        val startRangeText = getString(string = Strings.DateRangePickerStartHeadline)
        DateInputTextField(
            modifier = Modifier.weight(0.5f),
            label = {
                Text(startRangeText,
                    modifier = Modifier.semantics {
                        contentDescription = "$startRangeText, $pattern"
                    })
            },
            placeholder = { Text(pattern, modifier = Modifier.clearAndSetSemantics { }) },
            stateData = stateData,
            initialDate = stateData.selectedStartDate.value,
            onDateChanged = { date -> stateData.selectedStartDate.value = date },
            inputIdentifier = InputIdentifier.StartDateInput,
            dateInputValidator = dateInputValidator,
            dateInputFormat = dateInputFormat,
            locale = defaultLocale
        )
        val endRangeText = getString(string = Strings.DateRangePickerEndHeadline)
        DateInputTextField(
            modifier = Modifier.weight(0.5f),
            label = {
                Text(endRangeText,
                    modifier = Modifier.semantics {
                        contentDescription = "$endRangeText, $pattern"
                    })
            },
            placeholder = { Text(pattern, modifier = Modifier.clearAndSetSemantics { }) },
            stateData = stateData,
            initialDate = stateData.selectedEndDate.value,
            onDateChanged = { date -> stateData.selectedEndDate.value = date },
            inputIdentifier = InputIdentifier.EndDateInput,
            dateInputValidator = dateInputValidator,
            dateInputFormat = dateInputFormat,
            locale = defaultLocale
        )
    }
}

private val TextFieldSpacing = 8.dp
