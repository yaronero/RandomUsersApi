package com.example.randomusersapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserModelDb::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}