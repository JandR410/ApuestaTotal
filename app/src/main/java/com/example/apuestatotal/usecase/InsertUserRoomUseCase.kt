package com.example.apuestatotal.usecase

import com.example.apuestatotal.data.model.User
import com.example.apuestatotal.domain.repository.UserRepository
import javax.inject.Inject

class InsertUserRoomUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: List<User>) {
        val users = user.map { it.toEntity() }
        userRepository.insertUsers(users)
    }
}