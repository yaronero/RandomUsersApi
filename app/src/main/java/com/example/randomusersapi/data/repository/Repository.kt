package com.example.randomusersapi.data.repository

import com.example.randomusersapi.data.api.ApiService
import com.example.randomusersapi.data.api.RetrofitInstance
import com.example.randomusersapi.domain.User
import com.example.randomusersapi.domain.apimodels.DataResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {

    private val list = mutableListOf<User>()

    fun loadUserData(sendUserList: (List<User>?) -> Unit) {
        val apiService = RetrofitInstance.getInstance().create(ApiService::class.java)

        val callback = apiService.getData()

        callback.enqueue(object : Callback<DataResult> {
            override fun onResponse(call: Call<DataResult>, response: Response<DataResult>) {
                response.body()?.results?.forEach {
                    list.add(
                        User(
                            it.login.uuid,
                            it.image.imageUrl,
                            it.name.firstName,
                            it.gender,
                            it.dob.age,
                            it.email
                        )
                    )
                }
                sendUserList(list)
            }

            override fun onFailure(call: Call<DataResult>, t: Throwable) {
                sendUserList(null)
            }
        })
    }

    fun getUserByUuid(uuid: String): User {
        return list.first { it.uuid == uuid }
    }

    private fun loadUserDataToDB() {

    }
}