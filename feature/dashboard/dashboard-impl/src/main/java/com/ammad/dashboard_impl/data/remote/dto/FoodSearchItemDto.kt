package com.ammad.dashboard_impl.data.remote.dto

data class FoodSearchItemDto(
    val aggregations: Aggregations,
    val currentPage: Int,
    val foodSearchCriteria: FoodSearchCriteria,
    val foods: List<Food>,
    val pageList: List<Int>,
    val totalHits: Int,
    val totalPages: Int
)