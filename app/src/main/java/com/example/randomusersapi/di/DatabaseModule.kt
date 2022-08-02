package com.example.randomusersapi.di

import com.example.randomusersapi.data.repository.DBRepositoryImpl
import com.example.randomusersapi.domain.DBRepository
import dagger.Binds
import dagger.Module

@Module
interface DatabaseModule {

    @Binds
    fun bindDatabaseRepository(impl: DBRepositoryImpl): DBRepository
}