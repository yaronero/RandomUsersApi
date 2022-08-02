package com.example.randomusersapi.di

import com.example.randomusersapi.presentation.userdetails.UserDetailsFragment
import com.example.randomusersapi.presentation.userlist.UserListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ApiServiceModule::class,
        UserDaoModule::class,
        DataBindingsModule::class
    ]
)
interface AppComponent {

    fun inject(fragment: UserListFragment)
    fun inject(fragment: UserDetailsFragment)
}