package com.example.randomusersapi.di

import com.example.randomusersapi.data.api.ApiService
import com.example.randomusersapi.data.repository.ApiRepositoryImpl
import com.example.randomusersapi.domain.ApiRepository
import dagger.Binds
import dagger.Module

@Module
interface ApiModule {

    @Binds
    fun bindApiService(apiService: ApiService): ApiService

    @Binds
    fun bindApiRepository(impl: ApiRepositoryImpl): ApiRepository
}