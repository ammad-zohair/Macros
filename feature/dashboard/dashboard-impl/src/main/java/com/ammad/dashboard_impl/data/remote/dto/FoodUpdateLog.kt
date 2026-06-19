package com.ammad.dashboard_impl.data.remote.dto

data class FoodUpdateLog(
    val availableDate: String,
    val brandName: String,
    val brandOwner: String,
    val brandedFoodCategory: String,
    val dataSource: String,
    val dataType: String,
    val description: String,
    val discontinuedDate: String,
    val fdcId: Int,
    val foodAttributes: List<Any>,
    val foodClass: String,
    val gtinUpc: String,
    val householdServingFullText: String,
    val ingredients: String,
    val marketCountry: String,
    val modifiedDate: String,
    val notaSignificantSourceOf: String,
    val packageWeight: String,
    val publicationDate: String,
    val servingSize: Double,
    val servingSizeUnit: String,
    val subbrandName: String
)