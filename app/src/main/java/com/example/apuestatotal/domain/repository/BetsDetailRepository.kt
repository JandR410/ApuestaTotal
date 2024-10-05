package com.example.apuestatotal.domain.repository

import androidx.paging.PagingSource
import com.example.apuestatotal.data.room.entity.BetEntity
import com.example.apuestatotal.data.room.entity.BetsHistoryEntity

interface BetsDetailRepository {
    fun getPagedBets(): PagingSource<Int, BetEntity>
    suspend fun insertBets(bets: List<BetEntity>)
    suspend fun clearBets()
}