package com.example.apuestatotal.domain.repository

import com.example.apuestatotal.data.model.Bet
import com.example.apuestatotal.data.model.BetsHistory
import com.example.apuestatotal.data.model.User

interface DataSource {
    suspend fun getBetsDetail(): List<Bet>
    suspend fun getUser(): List<User>
    suspend fun getBetsHistory(): List<BetsHistory>
}