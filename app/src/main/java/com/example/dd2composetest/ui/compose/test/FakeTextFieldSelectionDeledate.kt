package com.example.dd2composetest.ui.compose.test

import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextRange

/**
 * Return selection information for TextField.
 *
 * @param textLayoutResult a result of the text layout.
 * @param rawStartOffset unprocessed start offset calculated directly from input position
 * @param rawEndOffset unprocessed end offset calculated directly from input position
 * @param previousSelection previous selection result
 * @param isStartHandle true if the start handle is being dragged
 * @param adjustment selection is adjusted according to this param
 *
 * @return selected text range.
 */
internal fun getTextFieldSelection(
    textLayoutResult: TextLayoutResult?,
    rawStartOffset: Int,
    rawEndOffset: Int,
    previousSelection: TextRange?,
    isStartHandle: Boolean,
    adjustment: FakeSelectionAdjustment
): TextRange {
    textLayoutResult?.let {
        val textRange = TextRange(rawStartOffset, rawEndOffset)

        // When the previous selection is null, it's allowed to have collapsed selection.
        // So we can ignore the SelectionAdjustment.Character.
        if (previousSelection == null && adjustment == FakeSelectionAdjustment.Character) {
            return textRange
        }

        return adjustment.adjust(
            textLayoutResult = textLayoutResult,
            newRawSelectionRange = textRange,
            previousHandleOffset = -1,
            isStartHandle = isStartHandle,
            previousSelectionRange = previousSelection
        )
    }
    return TextRange(0, 0)
}