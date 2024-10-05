package com.example.apuestatotal.usecase

import com.example.apuestatotal.data.model.User
import com.example.apuestatotal.domain.repository.DataSource
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val dataSource: DataSource) {
    suspend operator fun invoke(): List<User> {
        return dataSource.getUser()
    }
}