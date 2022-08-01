package com.example.randomusersapi.presentation.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomusersapi.data.repository.RepositoryImpl
import com.example.randomusersapi.domain.User
import com.example.randomusersapi.utils.LOAD_AMOUNT
import kotlinx.coroutines.launch

class UserListViewModel(
    private val repository: RepositoryImpl
) : ViewModel() {

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>>
        get() = _userList

    private val _errorLoading = MutableLiveData<Boolean>()
    val errorLoading: LiveData<Boolean>
        get() = _errorLoading

    fun getUserList() {
        viewModelScope.launch {
            val loadedUserList = repository.loadUserData(
                _userList.value?.size?.div(LOAD_AMOUNT) ?: 0
            )
            if (loadedUserList.isNotEmpty()) {
                val currentList = userList.value ?: emptyList()
                _userList.postValue(currentList + loadedUserList)
                _errorLoading.postValue(false)
            } else {
                _errorLoading.postValue(true)
            }
        }
    }
}