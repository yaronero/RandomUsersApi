package com.example.randomusersapi.presentation.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.randomusersapi.base.BaseViewModel
import com.example.randomusersapi.data.db.UserDao
import com.example.randomusersapi.domain.User
import kotlinx.coroutines.launch

class UserListViewModel(
    userDao: UserDao
) : BaseViewModel(
    userDao = userDao
) {

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

    private fun uploadUserList(list: List<User>) {
        if (list.isNotEmpty()) {
            _userList.postValue(list)
            _errorLoading.postValue(false)
        } else {
            _errorLoading.postValue(true)
        }
    }
}