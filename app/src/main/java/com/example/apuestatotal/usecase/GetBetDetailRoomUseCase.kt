package com.example.apuestatotal.usecase

import androidx.paging.PagingSource
import com.example.apuestatotal.data.room.entity.BetEntity
import com.example.apuestatotal.domain.repository.BetsDetailRepository
import javax.inject.Inject

class GetBetDetailRoomUseCase @Inject constructor(
    private val betsDetailRepository: BetsDetailRepository
) {
    operator fun invoke(): PagingSource<Int, BetEntity> {
        return betsDetailRepository.getPagedBets()
    }
}