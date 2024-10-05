package com.example.apuestatotal.domain.repository

import com.example.apuestatotal.data.room.entity.UserEntity

interface UserRepository {
    suspend fun insertUsers(users: List<UserEntity>)
    suspend fun getAllUsers(): List<UserEntity>
}