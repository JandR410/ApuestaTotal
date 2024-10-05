package com.example.apuestatotal.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bets_history")
data class BetsHistoryEntity(
    val db: Int,
    val operation: Int,
    @PrimaryKey val game: String,
    val created_date: String,
    val status: String,
    val wager: Int,
    val winning: Int?,
    val odds: Double,
    val type: String,
    val account: String
)
