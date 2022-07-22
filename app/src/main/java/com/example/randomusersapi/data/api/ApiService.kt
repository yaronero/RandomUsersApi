package com.example.randomusersapi.data.api

import com.example.randomusersapi.domain.apimodels.DataResult
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/?page=$PAGE&results=$LIMIT&seed=$SEED")
    fun getData(): Call<DataResult>

    companion object {
        const val PAGE = 1
        const val LIMIT = 15
        const val SEED = "abc"
    }
}