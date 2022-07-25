package com.example.randomusersapi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.randomusersapi.data.db.UserDao
import com.example.randomusersapi.presentation.userdetails.UserDetailsViewModel
import com.example.randomusersapi.presentation.userlist.UserListViewModel

class ViewModelFactory(
    private val userDao: UserDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            UserDetailsViewModel::class.java -> UserDetailsViewModel(userDao)
            UserListViewModel::class.java -> UserListViewModel(userDao)
            else -> throw IllegalStateException("Unknown viewModel class: $modelClass")
        }
        return viewModel as T
    }
}