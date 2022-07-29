package com.example.randomusersapi.data.db

import android.app.Application
import androidx.room.Room
import com.example.randomusersapi.data.repository.Repository

class DatabaseInstance private constructor() {

    companion object {
        private var database: UsersDatabase? = null

        fun getInstance(application: Application): UsersDatabase {
            if (database == null) {
                synchronized(this) {
                    if (database == null) {
                        database = Room.databaseBuilder(
                            application,
                            UsersDatabase::class.java, Repository.DATABASE_NAME
                        ).build()
                    }
                }
            }
            return database!!
        }
    }
}