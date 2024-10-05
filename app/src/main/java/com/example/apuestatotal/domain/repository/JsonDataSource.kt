package com.example.apuestatotal.domain.repository

import android.content.Context
import com.example.apuestatotal.data.model.Bet
import com.example.apuestatotal.data.model.BetsHistory
import com.example.apuestatotal.data.model.User
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class JsonDataSource @Inject constructor(@ApplicationContext private val context: Context) :
    DataSource {
    override suspend fun getBetsDetail(): List<Bet> {
        return withContext(Dispatchers.IO) {
            val jsonString =
                context.assets.open("betsDetailHistory.json").bufferedReader().use { it.readText() }
            val result = Json.decodeFromString<List<Bet>>(jsonString)
            return@withContext result
        }
    }

    override suspend fun getUser(): List<User> {
        return withContext(Dispatchers.IO) {
            val jsonString =
                context.assets.open("user.json").bufferedReader().use { it.readText() }
            val result = Json.decodeFromString<List<User>>(jsonString)
            return@withContext result
        }
    }

    override suspend fun getBetsHistory(): List<BetsHistory> {
        return withContext(Dispatchers.IO) {
            val jsonString =
                context.assets.open("betsHistory.json").bufferedReader().use { it.readText() }
            val result = Json.decodeFromString<List<BetsHistory>>(jsonString)
            return@withContext result
        }
    }
}
