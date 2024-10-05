package com.example.apuestatotal.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.apuestatotal.converter.Converters
import com.example.apuestatotal.data.room.dao.BetsDetailDao
import com.example.apuestatotal.data.room.dao.BetsHistoryDao
import com.example.apuestatotal.data.room.dao.UserDao
import com.example.apuestatotal.data.room.entity.BetEntity
import com.example.apuestatotal.data.room.entity.BetsHistoryEntity
import com.example.apuestatotal.data.room.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        BetsHistoryEntity::class,
        BetEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun betHistoryDao(): BetsHistoryDao
    abstract fun betsDetailDao(): BetsDetailDao
}


