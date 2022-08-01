package com.example.randomusersapi.domain

interface Repository {

    suspend fun loadUserData(pageIndex: Int): List<User>

    suspend fun getUserByUuid(uuid: String): User
}