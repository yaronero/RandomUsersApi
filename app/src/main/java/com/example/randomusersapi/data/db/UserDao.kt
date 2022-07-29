package com.example.randomusersapi.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.randomusersapi.utils.LOAD_AMOUNT

@Dao
interface UserDao {

    @Query("SELECT * FROM users LIMIT $LOAD_AMOUNT OFFSET :startPosition * $LOAD_AMOUNT")
    suspend fun getRangeOfUsers(startPosition: Int): List<UserModelDb>

    @Query("SELECT * FROM users WHERE uuid=:userUuid LIMIT 1")
    suspend fun getUserByUuid(userUuid: String): UserModelDb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(usersList: List<UserModelDb>)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}