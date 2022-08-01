package com.example.randomusersapi.domain

interface ApiRepository {

    suspend fun loadUserData(): List<User>
}