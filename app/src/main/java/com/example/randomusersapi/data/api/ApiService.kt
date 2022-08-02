package com.example.randomusersapi.data.api

import com.example.randomusersapi.data.api.model.DataResult
import com.example.randomusersapi.utils.LOAD_AMOUNT
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/?results=$LOAD_AMOUNT")
    suspend fun getData(): Response<DataResult>

    companion object {
        const val BASE_URL = "https://randomuser.me/"
    }
}