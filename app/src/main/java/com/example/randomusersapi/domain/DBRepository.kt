package com.example.randomusersapi.domain

interface DBRepository {

    suspend fun insertAllUsers(list: List<User>)

    suspend fun deleteAllUsers()

    suspend fun getUserByUuid(uuid: String): User

    suspend fun getRangeOfUsers(startPosition: Int): List<User>
}