package com.example.randomusersapi.data.repository

import com.example.randomusersapi.data.UserMapper
import com.example.randomusersapi.data.api.ApiService
import com.example.randomusersapi.data.db.UserDao
import com.example.randomusersapi.domain.User

class Repository(
    private val userDao: UserDao,
    private val apiService: ApiService,
    private val mapper: UserMapper = UserMapper()
) {

    private var isFirstLoad = true
    private var pageIndex = 0

    suspend fun loadUserData(): List<User> {
        try {
            val response = apiService.getData()

            return if (response.isSuccessful) {
                if (isFirstLoad) {
                    isFirstLoad = false
                    userDao.deleteAllUsers()
                }
                val list = response.body()?.results?.map {
                    mapper.mapDataUserToDbModel(it)
                } ?: emptyList()
                userDao.insertAll(list)
                mapper.mapDbModelListToEntityList(userDao.getRangeOfUsers(pageIndex++))
            } else {
                onError()
            }
        } catch (e: Exception) {
            return onError()
        }
    }

    private suspend fun onError(): List<User> {
        val list =
            mapper.mapDbModelListToEntityList(userDao.getRangeOfUsers(pageIndex++))
        return list.ifEmpty {
            emptyList()
        }
    }

    suspend fun getUserByUuid(uuid: String): User {
        return mapper.mapDbModelToEntity(userDao.getUserByUuid(uuid))
    }

    companion object {
        const val DATABASE_NAME = "UsersDb"
    }
}