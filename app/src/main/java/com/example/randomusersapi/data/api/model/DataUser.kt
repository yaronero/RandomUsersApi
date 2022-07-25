package com.example.randomusersapi.data.api.model

import com.google.gson.annotations.SerializedName

data class DataUser(
    @SerializedName("login") val login: DataUuid,
    @SerializedName("picture") val image: DataImageUrl,
    @SerializedName("name") val name: DataFirstName,
    @SerializedName("gender") val gender: String,
    @SerializedName("dob") val dob: DataAge,
    @SerializedName("email") val email: String
)