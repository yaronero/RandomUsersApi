package com.example.randomusersapi.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserModelDb(
    @PrimaryKey val uuid: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "email") val email: String
)