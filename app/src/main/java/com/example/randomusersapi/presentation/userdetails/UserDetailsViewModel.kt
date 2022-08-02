package com.example.randomusersapi.presentation.userdetails

import androidx.lifecycle.*
import com.example.randomusersapi.domain.Repository
import com.example.randomusersapi.domain.User
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class UserDetailsViewModel @AssistedInject constructor(
    private val repository: Repository,
    @Assisted private val uuid: String
) : ViewModel() {

    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User>
        get() = _userInfo

    fun getUserDetails() {
        viewModelScope.launch {
            _userInfo.postValue(repository.getUserByUuid(uuid))
        }
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            uuid: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(uuid) as T
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(uuid: String): UserDetailsViewModel
    }
}