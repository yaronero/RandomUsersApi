package com.example.randomusersapi.data.repository

import com.example.randomusersapi.domain.User

class Repository(
    private val apiRepository: ApiRepository,
    private val dbRepository: DBRepository,
) {

    private var isFirstLoad = true

    suspend fun loadUserData(pageIndex: Int): List<User> {
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

    suspend fun getUserByUuid(uuid: String): User {
        return dbRepository.getUserByUuid(uuid)
    }
}