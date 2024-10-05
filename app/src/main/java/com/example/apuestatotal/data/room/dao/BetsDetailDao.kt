package com.example.apuestatotal.data.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.apuestatotal.data.room.entity.BetEntity
import com.example.apuestatotal.data.room.entity.BetsHistoryEntity

@Dao
interface BetsDetailDao {

    @Query("SELECT * FROM bets")
    fun getPagedBets(): PagingSource<Int, BetEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBets(bets: List<BetEntity>)

    @Query("DELETE FROM bets")
    suspend fun clearBets()
}
