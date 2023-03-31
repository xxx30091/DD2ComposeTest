package com.example.dd2composetest.ui.compose.fakecomponents

import androidx.compose.ui.input.key.KeyEvent

/**
 * Platform specific behavior for deselecting text selection in TextField and Text based on
 * special keys, such as the "Back" button on Android.
 *
 * Only return true if selection of should be immediately cancelled in response to this [KeyEvent]
 * as a special case such as the "Back" button on Android. This is not intended for events that
 * would naturally cancel selection due to cursor movement, such as pressing an arrow key.
 *
 * @return true if selection should be cancelled based on this KeyEvent
 */
internal expect fun KeyEvent.cancelsTextSelection(): Boolean

/**
 * macOS has a character/emoji palette, which has to be ordered by application. This
 * platform-specific helper implements this action on MacOS and noop on other platforms.
 */
internal expect fun showCharacterPalette()