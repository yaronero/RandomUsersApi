package com.example.randomusersapi.di

import com.example.randomusersapi.data.repository.ApiRepositoryImpl
import com.example.randomusersapi.data.repository.DBRepositoryImpl
import com.example.randomusersapi.domain.ApiRepository
import com.example.randomusersapi.domain.DBRepository
import dagger.Binds
import dagger.Module

@Module
interface DataBindingsModule {

    @Binds
    fun bindApiRepository(impl: ApiRepositoryImpl): ApiRepository

    @Binds
    fun bindDatabaseRepository(impl: DBRepositoryImpl): DBRepository
}