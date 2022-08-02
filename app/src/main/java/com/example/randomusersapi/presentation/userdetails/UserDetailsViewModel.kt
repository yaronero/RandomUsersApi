package com.example.randomusersapi.presentation.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomusersapi.domain.Repository
import com.example.randomusersapi.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User>
        get() = _userInfo

    fun getUserByUuid(uuid: String) {
        viewModelScope.launch {
            _userInfo.postValue(repository.getUserByUuid(uuid))
        }
    }
}