package com.example.randomusersapi.di

import android.content.Context
import androidx.room.Room
import com.example.randomusersapi.data.db.UserDao
import com.example.randomusersapi.data.db.UsersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserDaoModule {

    @Singleton
    @Provides
    fun provideUsersDatabase(@ApplicationContext context: Context): UsersDatabase {
        return Room.databaseBuilder(
            context,
            UsersDatabase::class.java, UsersDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideUserDao(usersDatabase: UsersDatabase): UserDao {
        return usersDatabase.userDao()
    }
}