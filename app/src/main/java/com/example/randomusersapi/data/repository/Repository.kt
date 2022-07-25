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

    suspend fun loadUserData(): List<User> {
        val response = apiService.getData()

        return if (response.isSuccessful) {
            userDao.deleteAllUsers()
            val list = response.body()?.results?.map {
                mapper.mapDataUserToDbModel(it)
            } ?: emptyList()
            userDao.insertAll(list)
            mapper.mapDbModelListToEntityList(userDao.getAllUsers())
        } else {
            onError()
        }
    }

    private suspend fun onError(): List<User> {
        val list = mapper.mapDbModelListToEntityList(userDao.getAllUsers())
        return if (list.isEmpty()) {
            emptyList()
        } else {
            mapper.mapDbModelListToEntityList(userDao.getAllUsers())
        }
    }

    suspend fun getUserByUuid(uuid: String): User {
        return mapper.mapDbModelToEntity(userDao.getUserByUuid(uuid))
    }

    companion object {
        const val DATABASE_NAME = "UsersDb"
    }
}