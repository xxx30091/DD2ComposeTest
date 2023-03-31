package com.example.dd2composetest.ui.compose.fakecomponents

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MagnifierStyle
import androidx.compose.foundation.magnifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize

// TODO(b/139322105) Implement for Android when hardware keyboard is implemented
internal actual fun isCopyKeyEvent(keyEvent: KeyEvent) = false

// We use composed{} to read a local, but don't provide inspector info because the underlying
// magnifier modifier provides more meaningful inspector info.
@SuppressLint("ModifierInspectorInfo")
@OptIn(ExperimentalFoundationApi::class)
internal actual fun Modifier.selectionMagnifier(manager: SelectionManager): Modifier {
    // Avoid tracking animation state on older Android versions that don't support magnifiers.
    if (!MagnifierStyle.TextDefault.isSupported) {
        return this
    }

    return composed {
        val density = LocalDensity.current
        var magnifierSize by remember { mutableStateOf(IntSize.Zero) }
        animatedSelectionMagnifier(
            magnifierCenter = {
                calculateSelectionMagnifierCenterAndroid(manager, magnifierSize)
            },
            platformMagnifier = { center ->
                Modifier
                    .magnifier(
                        sourceCenter = { center() },
                        onSizeChanged = { size ->
                            magnifierSize = with(density) {
                                IntSize(size.width.roundToPx(), size.height.roundToPx())
                            }
                        },
                        style = MagnifierStyle.TextDefault
                    )
            }
        )
    }
}