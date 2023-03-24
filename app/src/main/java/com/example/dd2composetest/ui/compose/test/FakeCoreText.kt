package com.example.dd2composetest.ui.compose.test

import androidx.compose.foundation.text.InternalFoundationTextApi
import androidx.compose.foundation.text.TextDelegate
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Density

/**
 * Returns the [TextDelegate] passed as a [current] param if the input didn't change
 * otherwise creates a new [TextDelegate].
 */
@OptIn(InternalFoundationTextApi::class)
internal fun updateTextDelegate(
    current: TextDelegate,
    text: AnnotatedString,
    style: TextStyle,
    density: Density,
    fontFamilyResolver: FontFamily.Resolver,
    softWrap: Boolean = true,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLine: Int = Int.MAX_VALUE,
    placeholders: List<AnnotatedString.Range<Placeholder>>
): TextDelegate {
    // NOTE(text-perf-review): whenever we have remember intrinsic implemented, this might be a
    // lot slower than the equivalent `remember(a, b, c, ...) { ... }` call.
    return if (current.text != text ||
        current.style != style ||
        current.softWrap != softWrap ||
        current.overflow != overflow ||
        current.maxLines != maxLine ||
        current.density != density ||
        current.placeholders != placeholders ||
        current.fontFamilyResolver !== fontFamilyResolver
    ) {
        TextDelegate(
            text = text,
            style = style,
            softWrap = softWrap,
            overflow = overflow,
            maxLines = maxLine,
            density = density,
            fontFamilyResolver = fontFamilyResolver,
            placeholders = placeholders
        )
    } else {
        current
    }
}

@OptIn(InternalFoundationTextApi::class)
internal fun updateTextDelegate(
    current: TextDelegate,
    text: String,
    style: TextStyle,
    density: Density,
    fontFamilyResolver: FontFamily.Resolver,
    softWrap: Boolean = true,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLine: Int = Int.MAX_VALUE
): TextDelegate {
    // NOTE(text-perf-review): whenever we have remember intrinsic implemented, this might be a
    // lot slower than the equivalent `remember(a, b, c, ...) { ... }` call.
    return if (current.text.text != text ||
        current.style != style ||
        current.softWrap != softWrap ||
        current.overflow != overflow ||
        current.maxLines != maxLine ||
        current.density != density ||
        current.fontFamilyResolver !== fontFamilyResolver
    ) {
        TextDelegate(
            text = AnnotatedString(text),
            style = style,
            softWrap = softWrap,
            overflow = overflow,
            maxLines = maxLine,
            density = density,
            fontFamilyResolver = fontFamilyResolver,
        )
    } else {
        current
    }
}

