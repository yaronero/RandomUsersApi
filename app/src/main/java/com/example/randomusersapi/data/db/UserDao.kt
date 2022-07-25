package com.example.randomusersapi.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): List<UserModelDb>

    @Query("SELECT * FROM users WHERE uuid=:userUuid LIMIT 1")
    suspend fun getUserByUuid(userUuid: String): UserModelDb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(usersList: List<UserModelDb>)

    @Query("DELETE FROM users")
    fun deleteAllUsers()
}