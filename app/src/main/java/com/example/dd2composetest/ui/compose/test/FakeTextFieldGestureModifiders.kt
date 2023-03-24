package com.example.dd2composetest.ui.compose.test

import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.pointerInput

// Touch selection
internal fun Modifier.longPressDragGestureFilter(
    observer: TextDragObserver,
    enabled: Boolean
) = if (enabled) {
    this.pointerInput(observer) { detectDragGesturesAfterLongPressWithObserver(observer) }
} else {
    this
}

// Focus modifiers
internal fun Modifier.textFieldFocusModifier(
    enabled: Boolean,
    focusRequester: FocusRequester,
    interactionSource: MutableInteractionSource?,
    onFocusChanged: (FocusState) -> Unit
) = this
    .focusRequester(focusRequester)
    .onFocusChanged(onFocusChanged)
    .focusable(interactionSource = interactionSource, enabled = enabled)

// Mouse
internal fun Modifier.mouseDragGestureDetector(
    observer: MouseSelectionObserver,
    enabled: Boolean
) = if (enabled) Modifier.pointerInput(observer) {
    mouseSelectionDetector(observer)
} else this