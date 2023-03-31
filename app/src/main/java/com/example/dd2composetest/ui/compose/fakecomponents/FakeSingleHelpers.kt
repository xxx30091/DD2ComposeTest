package com.example.dd2composetest.ui.compose.fakecomponents

import androidx.compose.ui.text.TextRange

///** StringBuilder.appendCodePoint is already defined on JVM so it's called appendCodePointX. */
//internal expect fun StringBuilder.appendCodePointX(codePoint: Int): StringBuilder
//
///**
// * Returns the index of the character break preceding [index].
// */
//internal expect fun String.findPrecedingBreak(index: Int): Int
//
///**
// * Returns the index of the character break following [index]. Returns -1 if there are no more
// * breaks before the end of the string.
// */
//internal expect fun String.findFollowingBreak(index: Int): Int

internal fun CharSequence.findParagraphStart(startIndex: Int): Int {
    for (index in startIndex - 1 downTo 1) {
        if (this[index - 1] == '\n') {
            return index
        }
    }
    return 0
}

internal fun CharSequence.findParagraphEnd(startIndex: Int): Int {
    for (index in startIndex + 1 until this.length) {
        if (this[index] == '\n') {
            return index
        }
    }
    return this.length
}

/**
 * Returns the text range of the paragraph at the given character offset.
 *
 * Paragraphs are separated by Line Feed character (\n).
 */
internal fun CharSequence.getParagraphBoundary(index: Int): TextRange {
    return TextRange(findParagraphStart(index), findParagraphEnd(index))
}