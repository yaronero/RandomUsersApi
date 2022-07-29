package com.example.randomusersapi.data.repository

import com.example.randomusersapi.data.UserMapper
import com.example.randomusersapi.data.api.ApiService
import com.example.randomusersapi.domain.User
import retrofit2.HttpException

class ApiRepository(
    private val apiService: ApiService,
    private val mapper: UserMapper = UserMapper()
) {

    suspend fun loadUserData(): List<User> {
        val response = apiService.getData()

        return if (response.isSuccessful) {
            response.body()?.results?.map {
                mapper.mapDataUserToEntity(it)
            } ?: emptyList()
        } else {
            throw HttpException(response)
        }
    }

}