package com.example.randomusersapi.data.repository

import com.example.randomusersapi.domain.ApiRepository
import com.example.randomusersapi.domain.DBRepository
import com.example.randomusersapi.domain.Repository
import com.example.randomusersapi.domain.User
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiRepository: ApiRepository,
    private val dbRepository: DBRepository,
) : Repository {

    private var isFirstLoad = true

    override suspend fun loadUserData(pageIndex: Int): List<User> {
        return try {
            val userList = apiRepository.loadUserData()

            if (isFirstLoad) {
                isFirstLoad = false
                dbRepository.deleteAllUsers()
            }

            dbRepository.insertAllUsers(userList)

            userList
        } catch (e: Exception) {
            onError(pageIndex)
        }
    }

    private suspend fun onError(pageIndex: Int): List<User> {
        val list = dbRepository.getRangeOfUsers(pageIndex + 1)
        return list.ifEmpty {
            emptyList()
        }
    }

    override suspend fun getUserByUuid(uuid: String): User {
        return dbRepository.getUserByUuid(uuid)
    }
}