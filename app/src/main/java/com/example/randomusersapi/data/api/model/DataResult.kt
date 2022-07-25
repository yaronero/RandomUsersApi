package com.example.randomusersapi.data.api.model

import com.google.gson.annotations.SerializedName

data class DataResult(
    @SerializedName("results") val results: List<DataUser>
)