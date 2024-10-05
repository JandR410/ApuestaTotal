package com.example.apuestatotal.usecase

import com.example.apuestatotal.data.room.entity.UserEntity
import com.example.apuestatotal.domain.repository.UserRepository
import javax.inject.Inject

class GetUserRoomUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): List<UserEntity> {
        return userRepository.getAllUsers()
    }
}