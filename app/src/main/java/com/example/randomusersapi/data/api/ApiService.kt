package com.example.randomusersapi.data.api

import com.example.randomusersapi.data.api.model.DataResult
import com.example.randomusersapi.utils.LOAD_AMOUNT
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("api/?results=$LOAD_AMOUNT")
    suspend fun getData(): Response<DataResult>

    companion object {
        private var apiService: ApiService? = null

        fun getInstance(): ApiService {
            if (apiService == null) {
                synchronized(this) {
                    if (apiService == null) {
                        apiService = Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(ApiService::class.java)
                    }
                }
            }
            return apiService!!
        }

        private const val BASE_URL = "https://randomuser.me/"
    }
}