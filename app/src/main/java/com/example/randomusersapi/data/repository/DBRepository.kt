package com.example.randomusersapi.data.repository

import com.example.randomusersapi.data.UserMapper
import com.example.randomusersapi.data.db.UserDao
import com.example.randomusersapi.domain.User

class DBRepository(
    private val userDao: UserDao,
    private val mapper: UserMapper = UserMapper()
) {

    suspend fun insertAllUsers(list: List<User>) {
        userDao.insertAll(mapper.mapEntityListToDbModelList(list))
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

    suspend fun getAllUsers(): List<User> {
        return mapper.mapDbModelListToEntityList(userDao.getAllUsers())
    }

    suspend fun getUserByUuid(uuid: String): User {
        return mapper.mapDbModelToEntity(userDao.getUserByUuid(uuid))
    }

    suspend fun getRangeOfUsers(startPosition: Int): List<User> {
        return mapper.mapDbModelListToEntityList(userDao.getRangeOfUsers(startPosition))
    }
}