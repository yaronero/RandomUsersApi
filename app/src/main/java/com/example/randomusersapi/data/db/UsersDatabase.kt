package com.example.randomusersapi.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserModelDb::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var database: UsersDatabase? = null

        fun getInstance(application: Application): UsersDatabase {
            if (database == null) {
                synchronized(this) {
                    if (database == null) {
                        database = Room.databaseBuilder(
                            application,
                            UsersDatabase::class.java, DATABASE_NAME
                        ).build()
                    }
                }
            }
            return database!!
        }

        private const val DATABASE_NAME = "UsersDb"
    }
}