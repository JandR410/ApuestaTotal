package com.example.apuestatotal.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BBSelection(
    @SerialName("SelectionId") val selectionId: Long,
    @SerialName("SelectionName") val selectionName: String,
    @SerialName("MarketName") val marketName: String,
    @SerialName("EarlyPayout") val earlyPayout: Boolean,
    @SerialName("BoreDraw") val boreDraw: Boolean,
    @SerialName("SelectionStatus") val selectionStatus: Int
)