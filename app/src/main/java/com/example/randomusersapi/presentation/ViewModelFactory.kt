package com.example.randomusersapi.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.randomusersapi.presentation.userdetails.UserDetailsViewModel
import com.example.randomusersapi.presentation.userlist.UserListViewModel

class ViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            UserDetailsViewModel::class.java -> UserDetailsViewModel(application)
            UserListViewModel::class.java -> UserListViewModel(application)
            else -> throw IllegalStateException("Unknown viewModel class: $modelClass")
        }
        return viewModel as T
    }
}