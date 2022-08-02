package com.example.randomusersapi.di

import androidx.room.Room
import com.example.randomusersapi.data.UserMapper
import com.example.randomusersapi.data.api.ApiService
import com.example.randomusersapi.data.api.ApiService.Companion.BASE_URL
import com.example.randomusersapi.data.db.UsersDatabase
import com.example.randomusersapi.data.db.UsersDatabase.Companion.DATABASE_NAME
import com.example.randomusersapi.data.repository.ApiRepositoryImpl
import com.example.randomusersapi.data.repository.DBRepositoryImpl
import com.example.randomusersapi.data.repository.RepositoryImpl
import com.example.randomusersapi.domain.ApiRepository
import com.example.randomusersapi.domain.DBRepository
import com.example.randomusersapi.domain.Repository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<ApiService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    single {
        Room.databaseBuilder(
            get(),
            UsersDatabase::class.java, DATABASE_NAME
        ).build().userDao()
    }

    factory {
        UserMapper()
    }

    factory<ApiRepository> {
        ApiRepositoryImpl(get(), get())
    }

    factory<DBRepository> {
        DBRepositoryImpl(get(), get())
    }

    factory<Repository> {
        RepositoryImpl(get(), get())
    }
}

