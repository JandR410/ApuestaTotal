package com.example.apuestatotal.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BetSelection(
    @SerialName("SelectionId") val selectionId: Long,
    @SerialName("SelectionStatus") val selectionStatus: Int,
    @SerialName("Price") val price: String,
    @SerialName("Name") val name: String,
    @SerialName("Spec") val spec: String?,
    @SerialName("MarketTypeId") val marketTypeId: Int,
    @SerialName("MarketId") val marketId: Int,
    @SerialName("MarketName") val marketName: String,
    @SerialName("IsLive") val isLive: Boolean,
    @SerialName("IsBetBuilder") val isBetBuilder: Boolean,
    @SerialName("IsBanker") val isBanker: Boolean,
    @SerialName("IsVirtual") val isVirtual: Boolean,
    @SerialName("BBSelections") val bbSelections: List<BBSelection>? = null,
    @SerialName("EventId") val eventId: Long,
    @SerialName("EventCode") val eventCode: String?,
    @SerialName("FeedEventId") val feedEventId: Long,
    @SerialName("EventName") val eventName: String,
    @SerialName("SportTypeId") val sportTypeId: Int,
    @SerialName("CategoryId") val categoryId: Int,
    @SerialName("CategoryName") val categoryName: String?,
    @SerialName("ChampId") val champId: Int,
    @SerialName("ChampName") val champName: String?,
    @SerialName("EventScore") val eventScore: String?,
    @SerialName("GameTime") val gameTime: String?,
    @SerialName("EventDate") val eventDate: String,
    @SerialName("PitcherInfo") val pitcherInfo: String?,
    @SerialName("Runners") val runners: String?,
    @SerialName("ExtraEventInfo") val extraEventInfo: String?,
    @SerialName("RC") val rc: Boolean,
    @SerialName("LiveInfoAtEventMinute") val liveInfoAtEventMinute: String?,
    @SerialName("IsLiveOrVirtual") val isLiveOrVirtual: Boolean,
    @SerialName("EarlyPayout") val earlyPayout: Boolean,
    @SerialName("BoreDraw") val boreDraw: Boolean,
    @SerialName("DeadHeatFactor") val deadHeatFactor: String?,
    @SerialName("DbId") val dbId: Int
)