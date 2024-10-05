package com.example.apuestatotal.data.model

import com.example.apuestatotal.data.room.entity.UserEntity
import kotlinx.serialization.Serializable

@Serializable
data class User (
    val name: String,
    val correo: String,
    val password: String,
) {
    fun toEntity (): UserEntity {
        return UserEntity(
            name = name,
            correo = correo,
            password = password
        )
    }
}