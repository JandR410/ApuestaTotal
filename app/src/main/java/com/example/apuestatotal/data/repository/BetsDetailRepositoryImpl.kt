package com.example.apuestatotal.data.repository

import androidx.paging.PagingSource
import com.example.apuestatotal.data.room.dao.BetsDetailDao
import com.example.apuestatotal.data.room.entity.BetEntity
import com.example.apuestatotal.domain.repository.BetsDetailRepository
import javax.inject.Inject

class BetsDetailRepositoryImpl @Inject constructor(
    private val betsDetailDao: BetsDetailDao
) : BetsDetailRepository {
    override fun getPagedBets(): PagingSource<Int, BetEntity> {
        return betsDetailDao.getPagedBets()
    }

    override suspend fun insertBets(bets: List<BetEntity>) {
        return betsDetailDao.insertBets(bets)
    }

    override suspend fun clearBets() {
        return betsDetailDao.clearBets()
    }
}