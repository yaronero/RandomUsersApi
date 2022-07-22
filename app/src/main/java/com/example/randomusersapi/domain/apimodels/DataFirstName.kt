package com.example.randomusersapi.domain.apimodels

import com.google.gson.annotations.SerializedName

data class DataFirstName(
    @SerializedName("first") val firstName: String
)