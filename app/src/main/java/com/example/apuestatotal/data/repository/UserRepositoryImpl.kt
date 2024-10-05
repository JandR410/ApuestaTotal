package com.example.apuestatotal.data.repository

import com.example.apuestatotal.data.room.dao.UserDao
import com.example.apuestatotal.data.room.entity.UserEntity
import com.example.apuestatotal.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun insertUsers(users: List<UserEntity>) {
        userDao.insertUsers(users)
    }
    override suspend fun getAllUsers(): List<UserEntity> {
        return userDao.getAllUsers()
    }
}