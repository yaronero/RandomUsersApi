package com.example.randomusersapi.data

import com.example.randomusersapi.data.db.UserModelDb
import com.example.randomusersapi.domain.User
import com.example.randomusersapi.data.api.model.DataUser

class UserMapper {

    fun mapDbModelToEntity(userModelDb: UserModelDb): User {
        return User(
            uuid = userModelDb.uuid,
            imageUrl = userModelDb.imageUrl,
            firstName = userModelDb.firstName,
            gender = userModelDb.gender,
            age = userModelDb.age,
            email = userModelDb.email
        )
    }

    fun mapDbModelListToEntityList(userModelDbList: List<UserModelDb>) = userModelDbList.map {
        mapDbModelToEntity(it)
    }

    fun mapDataUserToDbModel(dataUser: DataUser): UserModelDb {
        return UserModelDb(
            uuid = dataUser.login.uuid,
            imageUrl = dataUser.image.imageUrl,
            firstName = dataUser.name.firstName,
            gender = dataUser.gender,
            age = dataUser.dob.age,
            email = dataUser.email
        )
    }
}