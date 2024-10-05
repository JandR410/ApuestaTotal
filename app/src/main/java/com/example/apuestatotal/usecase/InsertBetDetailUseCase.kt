package com.example.apuestatotal.usecase

import com.example.apuestatotal.data.model.Bet
import com.example.apuestatotal.domain.repository.BetsDetailRepository
import javax.inject.Inject

class InsertBetDetailUseCase @Inject constructor(
    private val betsDetailRepository: BetsDetailRepository
) {
    suspend operator fun invoke(bet: List<Bet>) {
        val bets = bet.map { it.toEntity() }
        betsDetailRepository.insertBets(bets)
    }
}