package com.example.randomusersapi.base

import androidx.lifecycle.ViewModel
import com.example.randomusersapi.data.api.ApiService
import com.example.randomusersapi.data.api.RetrofitInstance
import com.example.randomusersapi.data.db.UserDao
import com.example.randomusersapi.data.repository.Repository

abstract class BaseViewModel(
    userDao: UserDao
) : ViewModel() {

    private val apiService by lazy {
        RetrofitInstance.getInstance().create(ApiService::class.java)
    }

    val repository = Repository(userDao, apiService)
}