package com.ammad.dashboard_impl.data.repository

import com.ammad.dashboard_impl.BuildConfig
import com.ammad.dashboard_impl.data.mapper.toDomain
import com.ammad.dashboard_impl.data.remote.api.DashboardApiService
import com.ammad.dashboard_impl.domain.model.FoodItem
import com.ammad.dashboard_impl.domain.model.FoodSearchItem
import com.ammad.dashboard_impl.domain.repository.DashboardRepository
import com.ammad.network.ApiResult
import com.ammad.network.safeApiCall


class DashboardRepositoryImpl(
    private val api: DashboardApiService
) : DashboardRepository {

    override suspend fun getFood(
        foodId: Int,
    ): ApiResult<FoodItem> {
        return when (val result = safeApiCall { api.getFood(foodId, BuildConfig.API_KEY) }) {
            is ApiResult.Success -> ApiResult.Success(result.data.toDomain())
            is ApiResult.Error -> ApiResult.Error(result.exception)
        }
    }

    override suspend fun searchFood(
        query: String
    ): ApiResult<FoodSearchItem> {
        return when (val result = safeApiCall { api.searchFood(BuildConfig.API_KEY, query) }) {
            is ApiResult.Success -> ApiResult.Success(result.data.toDomain())
            is ApiResult.Error -> ApiResult.Error(result.exception)
        }
    }
}