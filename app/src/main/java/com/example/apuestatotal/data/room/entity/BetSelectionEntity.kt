package com.example.apuestatotal.data.room.entity

import androidx.room.TypeConverters
import com.example.apuestatotal.converter.Converters

data class BetSelectionEntity(
    val selectionId: Long,
    val selectionStatus: Int,
    val price: String,
    val name: String,
    val spec: String?,
    val marketTypeId: Int,
    val marketId: Int,
    val marketName: String,
    val isLive: Boolean,
    val isBetBuilder: Boolean,
    val isBanker: Boolean,
    val isVirtual: Boolean,
    @TypeConverters(Converters::class)
    val bbSelections: List<BBSelectionEntity>? = null,
    val eventId: Long,
    val eventCode: String?,
    val feedEventId: Long,
    val eventName: String,
    val sportTypeId: Int,
    val categoryId: Int,
    val categoryName: String?,
    val champId: Int,
    val champName: String?,
    val eventScore: String?,
    val gameTime: String?,
    val eventDate: String,
    val pitcherInfo: String?,
    val runners: String?,
    val extraEventInfo: String?,
    val rc: Boolean,
    val liveInfoAtEventMinute: String?,
    val isLiveOrVirtual: Boolean,
    val earlyPayout: Boolean,
    val boreDraw: Boolean,
    val deadHeatFactor: String?,
    val dbId: Int
)
