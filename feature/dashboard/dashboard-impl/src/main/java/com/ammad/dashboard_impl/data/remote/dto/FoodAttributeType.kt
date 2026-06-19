package com.ammad.dashboard_impl.data.remote.dto

data class FoodAttributeType(
    val description: String,
    val foodAttributes: List<FoodAttributeX>,
    val id: Int,
    val name: String
)