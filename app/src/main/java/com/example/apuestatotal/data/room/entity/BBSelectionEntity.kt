package com.example.apuestatotal.data.room.entity

data class BBSelectionEntity(
    val selectionId: Long,
    val selectionName: String,
    val marketName: String,
    val earlyPayout: Boolean,
    val boreDraw: Boolean,
    val selectionStatus: Int
)
