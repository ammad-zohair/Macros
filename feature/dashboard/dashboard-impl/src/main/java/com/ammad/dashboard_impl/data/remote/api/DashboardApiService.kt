package com.ammad.dashboard_impl.data.remote.api

import com.ammad.dashboard_impl.data.remote.dto.FoodItemDto
import com.ammad.dashboard_impl.data.remote.dto.FoodSearchItemDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DashboardApiService {

    @GET("v1/food/{fdcId}")
    suspend fun getFood(
        @Path("fdcId") foodId: Int,
        @Query("api_key") apiKey: String
    ): Response<FoodItemDto>

    @GET("v1/foods/search")
    suspend fun searchFood(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Response<FoodSearchItemDto>
}