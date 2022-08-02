package com.example.randomusersapi.di

import com.example.randomusersapi.presentation.userdetails.UserDetailsViewModel
import com.example.randomusersapi.presentation.userlist.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        UserListViewModel(get())
    }

    viewModel {
        UserDetailsViewModel(get())
    }
}