package com.example.apuestatotal.usecase

import com.example.apuestatotal.data.model.Bet
import com.example.apuestatotal.domain.repository.DataSource
import javax.inject.Inject

class GetBetsDetailUseCase @Inject constructor(private val dataSource: DataSource) {
    suspend operator fun invoke(): List<Bet> {
        return dataSource.getBetsDetail()
    }
}