package com.example.randomusersapi.data.repository

import com.example.randomusersapi.data.UserMapper
import com.example.randomusersapi.data.api.ApiService
import com.example.randomusersapi.data.db.UserDao
import com.example.randomusersapi.domain.User
import com.example.randomusersapi.data.api.model.DataResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(
    private val userDao: UserDao,
    private val apiService: ApiService,
    private val mapper: UserMapper = UserMapper()
) {

    fun loadUserData(onUserReceived: (List<User>) -> Unit) {
        val callback = apiService.getData()

        callback.enqueue(object : Callback<DataResult> {
            override fun onResponse(call: Call<DataResult>, response: Response<DataResult>) {
                CoroutineScope(Dispatchers.IO).launch {
                    userDao.deleteAllUsers()
                    val list = response.body()?.results?.map {
                        mapper.mapDataUserToDbModel(it)
                    } ?: emptyList()
                    userDao.insertAll(list)
                    onUserReceived(mapper.mapDbModelListToEntityList(userDao.getAllUsers()))
                }
            }

            override fun onFailure(call: Call<DataResult>, t: Throwable) {
                CoroutineScope(Dispatchers.IO).launch {
                    onError(onUserReceived)
                }
            }
        })
    }

    private suspend fun onError(onUserReceived: (List<User>) -> Unit) {
        val list = mapper.mapDbModelListToEntityList(userDao.getAllUsers())
        if (list.isEmpty()) {
            onUserReceived(emptyList())
        } else {
            onUserReceived(mapper.mapDbModelListToEntityList(userDao.getAllUsers()))
        }
    }

    suspend fun getUserByUuid(uuid: String): User {
        return mapper.mapDbModelToEntity(userDao.getUserByUuid(uuid))
    }

    companion object {
        const val DATABASE_NAME = "UsersDb"
    }
}