package com.example.randomusersapi.data.repository

import com.example.randomusersapi.domain.User

class Repository(
    private val apiRepository: ApiRepository,
    private val dbRepository: DBRepository,
) {

    private var isFirstLoad = true
    private var pageIndex = 0

    suspend fun loadUserData(): List<User> {
        return try {
            val userList = apiRepository.uploadUserData()

            if (isFirstLoad) {
                isFirstLoad = false
                dbRepository.deleteAllUsers()
            }

            dbRepository.insertAllUsers(userList)

            dbRepository.getRangeOfUsers(pageIndex++)
        } catch (e: Exception) {
            onError()
        }
    }

    private suspend fun onError(): List<User> {
        val list = dbRepository.getRangeOfUsers(pageIndex++)
        return list.ifEmpty {
            emptyList()
        }
    }

    suspend fun getUserByUuid(uuid: String): User {
        return dbRepository.getUserByUuid(uuid)
    }

    companion object {
        const val DATABASE_NAME = "UsersDb"
    }
}