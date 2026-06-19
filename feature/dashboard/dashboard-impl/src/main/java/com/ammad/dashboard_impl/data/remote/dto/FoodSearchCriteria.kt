package com.ammad.dashboard_impl.data.remote.dto

data class FoodSearchCriteria(
    val generalSearchInput: String,
    val numberOfResultsPerPage: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val query: String,
    val requireAllWords: Boolean
)