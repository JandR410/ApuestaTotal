package com.example.apuestatotal.data.repository

import androidx.paging.PagingSource
import com.example.apuestatotal.data.room.dao.BetsHistoryDao
import com.example.apuestatotal.data.room.entity.BetsHistoryEntity
import com.example.apuestatotal.domain.repository.BetsHistoryRepository
import javax.inject.Inject

class BetsHistoryRepositoryImpl @Inject constructor(
    private val betsHistoryDao: BetsHistoryDao
) : BetsHistoryRepository {

    override fun getPagedBets(): PagingSource<Int, BetsHistoryEntity> {
        return betsHistoryDao.getPagedBets()
    }

    override suspend fun searchBets(query: String): List<BetsHistoryEntity> {
        return betsHistoryDao.searchBets(query)
    }

    override suspend fun insertBets(bets: List<BetsHistoryEntity>) {
        betsHistoryDao.insertBets(bets)
    }

    override suspend fun clearBets() {
        betsHistoryDao.clearBets()
    }
}
