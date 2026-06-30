package com.ammad.dashboard_impl.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DataType(
    val Branded: Int,
    val Foundation: Int,
    @SerializedName("SR Legacy")
    val SR_Legacy: Int,
    @SerializedName("Survey (FNDDS)")
    val Survey: Int
)