package com.ammad.dashboard_impl.data.remote.dto

data class FoodItemDto(
    val availableDate: String,
    val brandOwner: String,
    val brandedFoodCategory: String,
    val dataSource: String,
    val dataType: String,
    val description: String,
    val discontinuedDate: String,
    val fdcId: Int,
    val foodAttributes: List<Any>,
    val foodClass: String,
    val foodComponents: List<Any>,
    val foodNutrients: List<FoodNutrient>,
    val foodPortions: List<Any>,
    val foodUpdateLog: List<FoodUpdateLog>,
    val gtinUpc: String,
    val householdServingFullText: String,
    val ingredients: String,
    val labelNutrients: LabelNutrients,
    val marketCountry: String,
    val modifiedDate: String,
    val publicationDate: String,
    val servingSize: Double,
    val servingSizeUnit: String
)