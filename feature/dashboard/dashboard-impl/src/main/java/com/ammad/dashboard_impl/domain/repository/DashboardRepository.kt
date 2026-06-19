package com.ammad.dashboard_impl.domain.repository

import com.ammad.dashboard_impl.data.remote.dto.FoodItemDto
import com.ammad.dashboard_impl.data.remote.dto.FoodSearchItemDto
import com.ammad.network.ApiResult

interface DashboardRepository {
    suspend fun getFood(foodId: Int): ApiResult<FoodItemDto>
    suspend fun searchFood(query: String): ApiResult<FoodSearchItemDto>
}