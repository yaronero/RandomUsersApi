package com.example.randomusersapi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.randomusersapi.data.repository.RepositoryImpl
import com.example.randomusersapi.presentation.userdetails.UserDetailsViewModel
import com.example.randomusersapi.presentation.userlist.UserListViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val repositoryImpl: RepositoryImpl
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            UserDetailsViewModel::class.java -> UserDetailsViewModel(repositoryImpl)
            UserListViewModel::class.java -> UserListViewModel(repositoryImpl)
            else -> throw IllegalStateException("Unknown viewModel class: $modelClass")
        }
        return viewModel as T
    }
}