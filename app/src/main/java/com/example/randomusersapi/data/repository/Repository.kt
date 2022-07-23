package com.example.randomusersapi.data.repository

import android.app.Application
import androidx.room.Room
import com.example.randomusersapi.data.UserMapper
import com.example.randomusersapi.data.api.ApiService
import com.example.randomusersapi.data.api.RetrofitInstance
import com.example.randomusersapi.data.db.UserModelDb
import com.example.randomusersapi.data.db.UsersDatabase
import com.example.randomusersapi.domain.User
import com.example.randomusersapi.domain.apimodels.DataResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(
    application: Application
) {

    private val userDao by lazy {
        Room.databaseBuilder(
            application,
            UsersDatabase::class.java, DATABASE_NAME
        ).build().userDao()
    }

    private val mapper by lazy {
        UserMapper()
    }

    fun loadUserData(sendUserList: (List<User>?) -> Unit) {
        val apiService = RetrofitInstance.getInstance().create(ApiService::class.java)

        val callback = apiService.getData()

        callback.enqueue(object : Callback<DataResult> {
            override fun onResponse(call: Call<DataResult>, response: Response<DataResult>) {
                CoroutineScope(Dispatchers.IO).launch {
                    userDao.deleteAllUsers()

                    response.body()?.results?.forEach {
                        userDao.insert(
                            UserModelDb(
                                it.login.uuid,
                                it.image.imageUrl,
                                it.name.firstName,
                                it.gender,
                                it.dob.age,
                                it.email
                            )
                        )
                    }

                    sendUserList(mapper.mapDbModelListToEntityList(userDao.getAllUsers()))
                }
            }

            override fun onFailure(call: Call<DataResult>, t: Throwable) {
                CoroutineScope(Dispatchers.IO).launch {
                    onError(sendUserList)
                }
            }
        })
    }

    private fun onError(sendUserList: (List<User>?) -> Unit) {
        val list = mapper.mapDbModelListToEntityList(userDao.getAllUsers())
        if (list.isEmpty()) {
            sendUserList(null)
        } else {
            sendUserList(mapper.mapDbModelListToEntityList(userDao.getAllUsers()))
        }
    }

    suspend fun getUserByUuid(uuid: String): User {
        return mapper.mapDbModelToEntity(userDao.getUserByUuid(uuid))
    }

    companion object {
        private const val DATABASE_NAME = "UsersDb"
    }
}