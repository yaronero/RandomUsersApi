package com.example.randomusersapi.di

import com.example.randomusersapi.presentation.userdetails.UserDetailsFragment
import com.example.randomusersapi.presentation.userlist.UserListFragment
import dagger.Component

@Component(modules = [
    ApplicationModule::class,
    ApiModule::class,
    UserDaoModule::class,
    DatabaseModule::class,
    ViewModelModule::class
])
interface AppComponent {

    fun inject(fragment: UserListFragment)
    //fun inject(fragment: UserDetailsFragment)
}