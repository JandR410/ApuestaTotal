package com.example.apuestatotal.data.model

import com.example.apuestatotal.data.room.entity.BBSelectionEntity
import com.example.apuestatotal.data.room.entity.BetEntity
import com.example.apuestatotal.data.room.entity.BetSelectionEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Bet(
    @SerialName("BetNivel") val betNivel: String,
    @SerialName("BetStarts") val betStarts: Int,
    @SerialName("BetStatusName") val betStatusName: String,
    @SerialName("BetTypeName") val betTypeName: String,
    @SerialName("BgSrc") val bgSrc: String,
    @SerialName("CashoutOdds") val cashoutOdds: String,
    @SerialName("TotalOdds") val totalOdds: String,
    @SerialName("TotalStake") val totalStake: String,
    @SerialName("TotalWin") val totalWin: String,
    @SerialName("CashoutValue") val cashoutValue: String,
    @SerialName("CreatedDate") val createdDate: String,
    @SerialName("BetSelections") val betSelections: List<BetSelection>,
    @SerialName("BetStatus") val betStatus: Int,
    @SerialName("BetType") val betType: Int,
    @SerialName("BetId") val betId: Long
) {
    fun toEntity(): BetEntity {
        return BetEntity(
            betNivel = betNivel,
            betStarts = betStarts,
            betStatusName = betStatusName,
            betTypeName = betTypeName,
            bgSrc = bgSrc,
            cashoutOdds = cashoutOdds,
            totalOdds = totalOdds,
            totalStake = totalStake,
            totalWin = totalWin,
            cashoutValue = cashoutValue,
            createdDate = createdDate,
            betSelections = mapBetSelections(betSelections),
            betStatus = betStatus,
            betType = betType,
            betId = betId
        )
    }

    private fun mapBetSelections(betSelections: List<BetSelection>): List<BetSelectionEntity> {
        return betSelections.map { betSelection ->
            BetSelectionEntity(
                selectionId = betSelection.selectionId,
                selectionStatus = betSelection.selectionStatus,
                price = betSelection.price,
                name = betSelection.name,
                spec = betSelection.spec,
                marketTypeId = betSelection.marketTypeId,
                marketId = betSelection.marketId,
                marketName = betSelection.marketName,
                isLive = betSelection.isLive,
                isBetBuilder = betSelection.isBetBuilder,
                isBanker = betSelection.isBanker,
                isVirtual = betSelection.isVirtual,
                bbSelections = mapBBSelections(betSelection.bbSelections),
                eventId = betSelection.eventId,
                eventCode = betSelection.eventCode,
                feedEventId = betSelection.feedEventId,
                eventName = betSelection.eventName,
                sportTypeId = betSelection.sportTypeId,
                categoryId = betSelection.categoryId,
                categoryName = betSelection.categoryName,
                champId = betSelection.champId,
                champName = betSelection.champName,
                eventScore = betSelection.eventScore,
                gameTime = betSelection.gameTime,
                eventDate = betSelection.eventDate,
                pitcherInfo = betSelection.pitcherInfo,
                runners = betSelection.runners,
                extraEventInfo = betSelection.extraEventInfo,
                rc = betSelection.rc,
                liveInfoAtEventMinute = betSelection.liveInfoAtEventMinute,
                isLiveOrVirtual = betSelection.isLiveOrVirtual,
                earlyPayout = betSelection.earlyPayout,
                boreDraw = betSelection.boreDraw,
                deadHeatFactor = betSelection.deadHeatFactor,
                dbId = betSelection.dbId
            )
        }
    }

    private fun mapBBSelections(bbSelections: List<BBSelection>?): List<BBSelectionEntity>? {
        return bbSelections?.map {
            BBSelectionEntity(
                selectionId = it.selectionId,
                selectionName = it.selectionName,
                marketName = it.marketName,
                earlyPayout = it.earlyPayout,
                boreDraw = it.boreDraw,
                selectionStatus = it.selectionStatus
            )
        }
    }
}