package com.example.randomusersapi.di

import android.app.Application
import androidx.room.Room
import com.example.randomusersapi.data.db.UserDao
import com.example.randomusersapi.data.db.UsersDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserDaoModule {

    @Singleton
    @Provides
    fun provideUsersDatabase(application: Application): UsersDatabase {
        return Room.databaseBuilder(
            application,
            UsersDatabase::class.java, UsersDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideUserDao(usersDatabase: UsersDatabase): UserDao {
        return usersDatabase.userDao()
    }
}