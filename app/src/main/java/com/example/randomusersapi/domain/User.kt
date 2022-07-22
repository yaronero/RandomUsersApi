package com.example.randomusersapi.domain

data class User(
    val uuid: String,
    val imageUrl: String,
    val firstName: String,
    val gender: String,
    val age: Int,
    val email: String
)
