package com.example.apuestatotal.usecase

import androidx.paging.PagingSource
import com.example.apuestatotal.data.room.entity.BetsHistoryEntity
import com.example.apuestatotal.domain.repository.BetsHistoryRepository
import javax.inject.Inject

class GetBetRoomUseCase @Inject constructor(
    private val betsHistoryRepository: BetsHistoryRepository
) {
    operator fun invoke(): PagingSource<Int, BetsHistoryEntity> {
        return betsHistoryRepository.getPagedBets()
    }
}