package com.example.randomusersapi.data.repository

import com.example.randomusersapi.data.UserMapper
import com.example.randomusersapi.data.api.ApiService
import com.example.randomusersapi.domain.User
import retrofit2.HttpException

class ApiRepository(
    private val apiService: ApiService,
    private val mapper: UserMapper = UserMapper()
) {

    suspend fun uploadUserData(): List<User> {
        val response = apiService.getData()

        return if (response.isSuccessful) {
            val list = response.body()?.results?.map {
                mapper.mapDataUserToDbModel(it)
            } ?: emptyList()
            mapper.mapDbModelListToEntityList(list)
        } else {
            throw HttpException(response)
        }
    }

}