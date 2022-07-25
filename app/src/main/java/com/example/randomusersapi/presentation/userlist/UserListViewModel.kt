package com.example.randomusersapi.presentation.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomusersapi.data.repository.Repository
import com.example.randomusersapi.domain.User
import kotlinx.coroutines.launch

class UserListViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>>
        get() = _userList

    private val _errorLoading = MutableLiveData<Boolean>()
    val errorLoading: LiveData<Boolean>
        get() = _errorLoading

    fun getUserList() {
        viewModelScope.launch {
            val userList = repository.loadUserData()
            if (userList.isNotEmpty()) {
                _userList.postValue(userList)
                _errorLoading.postValue(false)
            } else {
                _errorLoading.postValue(true)
            }
        }
    }
}