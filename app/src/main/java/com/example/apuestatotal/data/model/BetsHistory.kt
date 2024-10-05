package com.example.apuestatotal.data.model

import com.example.apuestatotal.data.room.entity.BetsHistoryEntity
import kotlinx.serialization.Serializable

@Serializable
data class BetsHistory(
    val db: Int,
    val operation: Int,
    val game: String,
    val created_date: String,
    val status: String,
    val wager: Int,
    val winning: Int?,
    val odds: Double,
    val type: String,
    val account: String
) {
    fun toEntity() = BetsHistoryEntity(
        db = db,
        operation = operation,
        game = game,
        created_date = created_date,
        status = status,
        wager = wager,
        winning = winning,
        odds = odds,
        type = type,
        account = account
    )
}
