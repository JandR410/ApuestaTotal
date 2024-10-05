package com.example.apuestatotal.data.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.apuestatotal.data.room.entity.BetsHistoryEntity

@Dao
interface BetsHistoryDao {

    @Query("SELECT * FROM bets_history ORDER BY created_date DESC")
    fun getPagedBets(): PagingSource<Int, BetsHistoryEntity>

    @Query("SELECT * FROM bets_history WHERE game LIKE :searchQuery OR account LIKE :searchQuery")
    fun searchBets(searchQuery: String): List<BetsHistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBets(bets: List<BetsHistoryEntity>)

    @Query("DELETE FROM bets_history")
    suspend fun clearBets()
}
