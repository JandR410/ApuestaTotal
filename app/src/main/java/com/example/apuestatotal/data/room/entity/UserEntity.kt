package com.example.apuestatotal.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity (
    val name: String,
    @PrimaryKey val correo: String,
    val password: String
)