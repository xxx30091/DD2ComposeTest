package com.example.dd2composetest.data.bean

data class Trend(
    val current: Int = 0,
    val thanLast: Int = 0,
    val nodes: List<TrendNodes> = listOf()
) {
    data class TrendNodes(
        val date: String = "",
        val value: Int = 0
    )
}
