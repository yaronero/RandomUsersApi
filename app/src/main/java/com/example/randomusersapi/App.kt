package com.example.randomusersapi

import android.app.Application
import com.example.randomusersapi.di.dataModule
import com.example.randomusersapi.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(dataModule, viewModelModule))
        }
    }
}