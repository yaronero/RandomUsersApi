package com.example.randomusersapi.data.repository

import com.example.randomusersapi.data.UserMapper
import com.example.randomusersapi.data.db.UserDao
import com.example.randomusersapi.domain.DBRepository
import com.example.randomusersapi.domain.User

class DBRepositoryImpl(
    private val userDao: UserDao,
    private val mapper: UserMapper = UserMapper()
) : DBRepository {

    override suspend fun insertAllUsers(list: List<User>) {
        userDao.insertAll(mapper.mapEntityListToDbModelList(list))
    }

    override suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

    override suspend fun getUserByUuid(uuid: String): User {
        return mapper.mapDbModelToEntity(userDao.getUserByUuid(uuid))
    }

    override suspend fun getRangeOfUsers(startPosition: Int): List<User> {
        return mapper.mapDbModelListToEntityList(userDao.getRangeOfUsers(startPosition))
    }
}