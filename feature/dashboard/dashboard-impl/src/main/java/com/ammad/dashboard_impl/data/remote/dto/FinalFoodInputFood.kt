package com.ammad.dashboard_impl.data.remote.dto

data class FinalFoodInputFood(
    val foodDescription: String,
    val gramWeight: Double,
    val id: Int,
    val portionCode: String,
    val portionDescription: String,
    val rank: Int,
    val retentionCode: Int,
    val srCode: Int,
    val unit: String,
    val value: Double
)