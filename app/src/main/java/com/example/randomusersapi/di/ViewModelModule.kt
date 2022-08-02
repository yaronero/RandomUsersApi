package com.example.randomusersapi.di

import androidx.lifecycle.ViewModel
import com.example.randomusersapi.presentation.userdetails.UserDetailsViewModel
import com.example.randomusersapi.presentation.userlist.UserListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface ViewModelModule {

    @IntoMap
    @StringKey("UserDetailsViewModel")
    @Binds
    fun bindExampleViewModel(impl: UserDetailsViewModel): ViewModel

    @IntoMap
    @StringKey("UserListViewModel")
    @Binds
    fun bindExampleViewModel2(impl: UserListViewModel): ViewModel
}