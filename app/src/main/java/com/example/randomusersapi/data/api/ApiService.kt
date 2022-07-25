package com.example.randomusersapi.data.api

import com.example.randomusersapi.data.api.model.DataResult
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/?page=$PAGE&results=$LIMIT&seed=$SEED")
    suspend fun getData(): Response<DataResult>

    companion object {
        const val PAGE = 1
        const val LIMIT = 15
        const val SEED = "abc"
    }
}