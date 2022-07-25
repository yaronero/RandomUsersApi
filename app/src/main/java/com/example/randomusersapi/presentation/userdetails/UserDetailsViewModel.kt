package com.example.randomusersapi.presentation.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.randomusersapi.base.BaseViewModel
import com.example.randomusersapi.data.db.UserDao
import com.example.randomusersapi.domain.User
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    userDao: UserDao
) : BaseViewModel(
    userDao = userDao
) {

    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User>
        get() = _userInfo

    fun getUserByUuid(uuid: String) {
        viewModelScope.launch {
            _userInfo.postValue(repository.getUserByUuid(uuid))
        }
    }
}