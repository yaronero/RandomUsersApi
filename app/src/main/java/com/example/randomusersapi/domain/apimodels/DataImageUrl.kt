package com.example.randomusersapi.domain.apimodels

import com.google.gson.annotations.SerializedName

data class DataImageUrl(
    @SerializedName("large") val imageUrl: String
)