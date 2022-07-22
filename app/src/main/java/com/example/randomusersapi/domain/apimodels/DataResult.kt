package com.example.randomusersapi.domain.apimodels

import com.google.gson.annotations.SerializedName

data class DataResult(
    @SerializedName("results") val results: List<DataUser>
)