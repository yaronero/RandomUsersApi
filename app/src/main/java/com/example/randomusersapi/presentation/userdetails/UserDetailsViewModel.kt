package com.example.randomusersapi.presentation.userdetails

import androidx.lifecycle.ViewModel
import com.example.randomusersapi.data.repository.Repository
import com.example.randomusersapi.domain.User

class UserDetailsViewModel : ViewModel() {

    private val repository = Repository

    fun getUserByUuid(uuid: String): User {
        return repository.getUserByUuid(uuid)
    }
}