package com.example.apuestatotal.converter

import androidx.room.TypeConverter
import com.example.apuestatotal.data.room.entity.BetSelectionEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromBetSelectionList(value: List<BetSelectionEntity>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toBetSelectionList(value: String): List<BetSelectionEntity>? {
        val listType = object : TypeToken<List<BetSelectionEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }
}
