package com.example.randomusersapi.di

import android.app.Application
import com.example.randomusersapi.data.db.UserDao
import com.example.randomusersapi.data.db.UsersDatabase
import dagger.Module
import dagger.Provides

@Module
class UserDaoModule {

    @Provides
    fun bindUserDao(application: Application): UserDao {
        return UsersDatabase.getInstance(application).userDao()
    }
}