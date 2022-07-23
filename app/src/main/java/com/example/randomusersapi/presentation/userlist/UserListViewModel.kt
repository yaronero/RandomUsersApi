package com.example.randomusersapi.presentation.userlist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomusersapi.data.repository.Repository
import com.example.randomusersapi.domain.User
import kotlinx.coroutines.launch

class UserListViewModel(
    application: Application
) : ViewModel() {

    private val repository = Repository(application)

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>>
        get() = _userList

    private val _errorLoading = MutableLiveData<Boolean>()
    val errorLoading: LiveData<Boolean>
        get() = _errorLoading

    fun getUserList() {
        viewModelScope.launch {
            repository.loadUserData(::uploadUserList)
        }
    }

    private fun uploadUserList(list: List<User>?) {
        list?.let {
            _userList.postValue(it)
            _errorLoading.postValue(false)
            return@uploadUserList
        }
        _errorLoading.postValue(true)
    }
}