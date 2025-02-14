package com.bridge.androidtechnicaltest.network

data class PagedResponse<T>(
    val itemCount: Int,
    val items: List<T>,
    val pageNumber: Int,
    val totalPages: Int
)