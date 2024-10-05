package com.example.apuestatotal.domain.repository

import androidx.paging.PagingSource
import com.example.apuestatotal.data.room.entity.BetsHistoryEntity

interface BetsHistoryRepository {
    fun getPagedBets(): PagingSource<Int, BetsHistoryEntity>
    suspend fun searchBets(query: String): List<BetsHistoryEntity>
    suspend fun insertBets(bets: List<BetsHistoryEntity>)
    suspend fun clearBets()
}