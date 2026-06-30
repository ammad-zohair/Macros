package com.ammad.dashboard_impl.domain.repository

import com.ammad.dashboard_impl.data.remote.dto.FoodItemDto
import com.ammad.dashboard_impl.data.remote.dto.FoodSearchItemDto
import com.ammad.dashboard_impl.domain.model.FoodItem
import com.ammad.dashboard_impl.domain.model.FoodSearchItem
import com.ammad.network.ApiResult

interface DashboardRepository {
    suspend fun getFood(foodId: Int): ApiResult<FoodItem>
    suspend fun searchFood(query: String): ApiResult<FoodSearchItem>
}