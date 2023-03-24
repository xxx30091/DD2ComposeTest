package com.example.dd2composetest.ui.compose.test

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.text.TextRange

// TODO Not read this part yet
@Stable
internal class TextFieldScrollerPosition(
    initialOrientation: Orientation,
    initial: Float = 0f
) {
    /*@VisibleForTesting*/
    constructor() : this(Orientation.Vertical)

    /**
     * Left or top offset. Takes values from 0 to [maximum].
     * Taken with the opposite sign defines the x or y position of the text field in the
     * horizontal or vertical scroller container correspondingly.
     */
    var offset by mutableStateOf(initial)

    /**
     * Maximum length by which the text field can be scrolled. Defined as a difference in
     * size between the scroller container and the text field.
     */
    var maximum by mutableStateOf(0f)
        private set

    /**
     * Keeps the cursor position before a new symbol has been typed or the text field has been
     * dragged. We check it to understand if the [offset] needs to be updated.
     */
    private var previousCursorRect: Rect = Rect.Zero

    /**
     * Keeps the previous selection data in TextFieldValue in order to identify what has changed
     * in the new selection, and decide which selection offset (start, end) to follow.
     */
    var previousSelection: TextRange = TextRange.Zero

    var orientation by mutableStateOf(initialOrientation, structuralEqualityPolicy())

    fun update(
        orientation: Orientation,
        cursorRect: Rect,
        containerSize: Int,
        textFieldSize: Int
    ) {
        val difference = (textFieldSize - containerSize).toFloat()
        maximum = difference

        if (cursorRect.left != previousCursorRect.left ||
            cursorRect.top != previousCursorRect.top
        ) {
            val vertical = orientation == Orientation.Vertical
            val cursorStart = if (vertical) cursorRect.top else cursorRect.left
            val cursorEnd = if (vertical) cursorRect.bottom else cursorRect.right
            coerceOffset(cursorStart, cursorEnd, containerSize)
            previousCursorRect = cursorRect
        }
        offset = offset.coerceIn(0f, difference)
    }

    /*@VisibleForTesting*/
    internal fun coerceOffset(cursorStart: Float, cursorEnd: Float, containerSize: Int) {
        val startVisibleBound = offset
        val endVisibleBound = startVisibleBound + containerSize
        val offsetDifference = when {
            // make bottom/end of the cursor visible
            //
            // text box
            // +----------------------+
            // |                      |
            // |                      |
            // |          cursor      |
            // |             |        |
            // +-------------|--------+
            //               |
            //
            cursorEnd > endVisibleBound -> cursorEnd - endVisibleBound

            // in rare cases when there's not enough space to fit the whole cursor, prioritise
            // the bottom/end of the cursor
            //
            //             cursor
            // text box      |
            // +-------------|--------+
            // |             |        |
            // +-------------|--------+
            //               |
            //
            cursorStart < startVisibleBound && cursorEnd - cursorStart > containerSize ->
                cursorEnd - endVisibleBound

            // make top/start of the cursor visible if there's enough space to fit the whole cursor
            //
            //               cursor
            // text box       |
            // +--------------|-------+
            // |              |       |
            // |                      |
            // |                      |
            // |                      |
            // +----------------------+
            //
            cursorStart < startVisibleBound && cursorEnd - cursorStart <= containerSize ->
                cursorStart - startVisibleBound

            // otherwise keep current offset
            else -> 0f
        }
        offset += offsetDifference
    }

    fun getOffsetToFollow(selection: TextRange): Int {
        return when {
            selection.start != previousSelection.start -> selection.start
            selection.end != previousSelection.end -> selection.end
            else -> selection.min
        }
    }

    companion object {
        val Saver = listSaver<TextFieldScrollerPosition, Any>(
            save = {
                listOf(it.offset, it.orientation == Orientation.Vertical)
            },
            restore = { restored ->
                TextFieldScrollerPosition(
                    if (restored[1] as Boolean) Orientation.Vertical else Orientation.Horizontal,
                    restored[0] as Float
                )
            }
        )
    }
}
