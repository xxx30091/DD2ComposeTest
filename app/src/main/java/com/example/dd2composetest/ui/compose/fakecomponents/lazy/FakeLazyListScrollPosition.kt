package com.example.dd2composetest.ui.compose.fakecomponents.lazy

import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.lazy.DataIndex
//import androidx.compose.foundation.lazy.LazyListItemProvider
//import androidx.compose.foundation.lazy.LazyListMeasureResult
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot


///**
// * Contains the current scroll position represented by the first visible item index and the first
// * visible item scroll offset.
// */
//internal class LazyListScrollPosition(
//    initialIndex: Int = 0,
//    initialScrollOffset: Int = 0
//) {
//    var index by mutableStateOf(DataIndex(initialIndex))
//
//    var scrollOffset by mutableStateOf(initialScrollOffset)
//        private set
//
//    private var hadFirstNotEmptyLayout = false
//
//    /** The last know key of the item at [index] position. */
//    private var lastKnownFirstItemKey: Any? = null
//
//    /**
//     * Updates the current scroll position based on the results of the last measurement.
//     */
//    fun updateFromMeasureResult(measureResult: LazyListMeasureResult) {
//        lastKnownFirstItemKey = measureResult.firstVisibleItem?.key
//        // we ignore the index and offset from measureResult until we get at least one
//        // measurement with real items. otherwise the initial index and scroll passed to the
//        // state would be lost and overridden with zeros.
//        if (hadFirstNotEmptyLayout || measureResult.totalItemsCount > 0) {
//            hadFirstNotEmptyLayout = true
//            val scrollOffset = measureResult.firstVisibleItemScrollOffset
//            check(scrollOffset >= 0f) { "scrollOffset should be non-negative ($scrollOffset)" }
//
//            Snapshot.withoutReadObservation {
//                update(
//                    DataIndex(measureResult.firstVisibleItem?.index ?: 0),
//                    scrollOffset
//                )
//            }
//        }
//    }
//
//    /**
//     * Updates the scroll position - the passed values will be used as a start position for
//     * composing the items during the next measure pass and will be updated by the real
//     * position calculated during the measurement. This means that there is no guarantee that
//     * exactly this index and offset will be applied as it is possible that:
//     * a) there will be no item at this index in reality
//     * b) item at this index will be smaller than the asked scrollOffset, which means we would
//     * switch to the next item
//     * c) there will be not enough items to fill the viewport after the requested index, so we
//     * would have to compose few elements before the asked index, changing the first visible item.
//     */
//    fun requestPosition(index: DataIndex, scrollOffset: Int) {
//        update(index, scrollOffset)
//        // clear the stored key as we have a direct request to scroll to [index] position and the
//        // next [checkIfFirstVisibleItemWasMoved] shouldn't override this.
//        lastKnownFirstItemKey = null
//    }
//
//    /**
//     * In addition to keeping the first visible item index we also store the key of this item.
//     * When the user provided custom keys for the items this mechanism allows us to detect when
//     * there were items added or removed before our current first visible item and keep this item
//     * as the first visible one even given that its index has been changed.
//     */
//    @ExperimentalFoundationApi
//    fun updateScrollPositionIfTheFirstItemWasMoved(itemProvider: LazyListItemProvider) {
//        Snapshot.withoutReadObservation {
//            update(findLazyListIndexByKey(lastKnownFirstItemKey, index, itemProvider), scrollOffset)
//        }
//    }
//
//    private fun update(index: DataIndex, scrollOffset: Int) {
//        require(index.value >= 0f) { "Index should be non-negative (${index.value})" }
//        if (index != this.index) {
//            this.index = index
//        }
//        if (scrollOffset != this.scrollOffset) {
//            this.scrollOffset = scrollOffset
//        }
//    }
//
//    private companion object {
//        /**
//         * Finds a position of the item with the given key in the lists. This logic allows us to
//         * detect when there were items added or removed before our current first item.
//         */
//        @ExperimentalFoundationApi
//        private fun findLazyListIndexByKey(
//            key: Any?,
//            lastKnownIndex: DataIndex,
//            itemProvider: LazyListItemProvider
//        ): DataIndex {
//            if (key == null) {
//                // there were no real item during the previous measure
//                return lastKnownIndex
//            }
//            if (lastKnownIndex.value < itemProvider.itemCount &&
//                key == itemProvider.getKey(lastKnownIndex.value)
//            ) {
//                // this item is still at the same index
//                return lastKnownIndex
//            }
//            val newIndex = itemProvider.keyToIndexMap[key]
//            if (newIndex != null) {
//                return DataIndex(newIndex)
//            }
//            // fallback to the previous index if we don't know the new index of the item
//            return lastKnownIndex
//        }
//    }
//}
