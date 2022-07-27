package com.example.randomusersapi.data.api

import com.example.randomusersapi.data.api.model.DataResult
import com.example.randomusersapi.utils.LOAD_AMOUNT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/?results=$LOAD_AMOUNT&seed=$SEED")
    suspend fun getData(
        @Query("page") pageIndex: Int
    ): Response<DataResult>

    companion object {
        private const val SEED = "abc"
    }
}