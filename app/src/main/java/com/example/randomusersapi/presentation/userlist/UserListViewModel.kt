package com.example.randomusersapi.presentation.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.randomusersapi.data.repository.Repository
import com.example.randomusersapi.domain.User

class UserListViewModel : ViewModel() {

    private val repository = Repository

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>>
        get() = _userList

    private val _errorLoading = MutableLiveData<Boolean>()
    val errorLoading: LiveData<Boolean>
        get() = _errorLoading

    fun getUserList() {
        repository.loadUserData(::uploadUserList)
    }

    private fun uploadUserList(list: List<User>?) {
        list?.let {
            _userList.value = it
            _errorLoading.value = false
            return@uploadUserList
        }
        _errorLoading.value = true
    }
}