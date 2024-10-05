package com.example.apuestatotal.usecase

import com.example.apuestatotal.data.model.BetsHistory
import com.example.apuestatotal.data.model.User
import com.example.apuestatotal.domain.repository.BetsHistoryRepository
import javax.inject.Inject

class InsertBetUseCase @Inject constructor(
    private val betsHistoryRepository: BetsHistoryRepository
) {
    suspend operator fun invoke(betsHistory: List<BetsHistory>) {
        val bets = betsHistory.map { it.toEntity() }
        betsHistoryRepository.insertBets(bets)
    }
}