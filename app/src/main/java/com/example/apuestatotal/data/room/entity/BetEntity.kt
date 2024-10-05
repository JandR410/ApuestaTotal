package com.example.apuestatotal.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.apuestatotal.converter.Converters

@Entity(tableName = "bets")
data class BetEntity(
    val betNivel: String,
    val betStarts: Int,
    val betStatusName: String,
    val betTypeName: String,
    val bgSrc: String,
    val cashoutOdds: String,
    val totalOdds: String,
    val totalStake: String,
    val totalWin: String,
    val cashoutValue: String,
    val createdDate: String,
    @TypeConverters(Converters::class)
    val betSelections: List<BetSelectionEntity>,
    val betStatus: Int,
    val betType: Int,
    @PrimaryKey val betId: Long
)
