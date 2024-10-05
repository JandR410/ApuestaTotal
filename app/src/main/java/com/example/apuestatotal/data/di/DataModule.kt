package com.example.apuestatotal.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.apuestatotal.data.repository.BetsDetailRepositoryImpl
import com.example.apuestatotal.data.repository.BetsHistoryRepositoryImpl
import com.example.apuestatotal.data.repository.UserRepositoryImpl
import com.example.apuestatotal.data.room.dao.BetsDetailDao
import com.example.apuestatotal.data.room.dao.BetsHistoryDao
import com.example.apuestatotal.data.room.dao.UserDao
import com.example.apuestatotal.data.room.database.AppDatabase
import com.example.apuestatotal.domain.repository.BetsDetailRepository
import com.example.apuestatotal.domain.repository.BetsHistoryRepository
import com.example.apuestatotal.domain.repository.DataSource
import com.example.apuestatotal.domain.repository.JsonDataSource
import com.example.apuestatotal.domain.repository.UserRepository
import com.example.apuestatotal.usecase.GetBetDetailRoomUseCase
import com.example.apuestatotal.usecase.GetBetRoomUseCase
import com.example.apuestatotal.usecase.GetBetsDetailUseCase
import com.example.apuestatotal.usecase.GetBetsHistoryUseCase
import com.example.apuestatotal.usecase.GetUserRoomUseCase
import com.example.apuestatotal.usecase.GetUserUseCase
import com.example.apuestatotal.usecase.InsertBetUseCase
import com.example.apuestatotal.usecase.InsertUserRoomUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideBetsHistoryDao(database: AppDatabase): BetsHistoryDao {
        return database.betHistoryDao()
    }

    @Provides
    @Singleton
    fun provideBetsDetailDao(database: AppDatabase): BetsDetailDao {
        return database.betsDetailDao()
    }

    @Provides
    @Singleton
    fun provideDataSource(context: Context): DataSource {
        return JsonDataSource(context)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepositoryImpl(userDao)
    }

    @Provides
    @Singleton
    fun provideBetsHistoryRepository(betsHistoryDao: BetsHistoryDao): BetsHistoryRepository {
        return BetsHistoryRepositoryImpl(betsHistoryDao)
    }

    @Provides
    @Singleton
    fun provideBetsDetailRepository(betsDetailDao: BetsDetailDao): BetsDetailRepository {
        return BetsDetailRepositoryImpl(betsDetailDao)
    }

    @Provides
    @Singleton
    fun provideGetDetailUseCase(dataSource: DataSource): GetBetsDetailUseCase {
        return GetBetsDetailUseCase(dataSource)
    }

    @Provides
    @Singleton
    fun provideGetBetsHistoryUseCase(dataSource: DataSource): GetBetsHistoryUseCase {
        return GetBetsHistoryUseCase(dataSource)
    }

    @Provides
    @Singleton
    fun provideGetUserRoomUseCase(userRepository: UserRepository): GetUserRoomUseCase {
        return GetUserRoomUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideGetUserUseCase(dataSource: DataSource): GetUserUseCase {
        return GetUserUseCase(dataSource)
    }

    @Provides
    @Singleton
    fun provideInsertUserRoomUseCase(userRepository: UserRepository): InsertUserRoomUseCase {
        return InsertUserRoomUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideInsertBetUseCase(betsHistory: BetsHistoryRepository): InsertBetUseCase {
        return InsertBetUseCase(betsHistory)
    }

    @Provides
    @Singleton
    fun provideGetBetDetailRoomUseCase(betsDetailRepository: BetsDetailRepository): GetBetDetailRoomUseCase {
        return GetBetDetailRoomUseCase(betsDetailRepository)
    }

    @Provides
    @Singleton
    fun provideGetBetRoomUseCase(betsHistoryRepository: BetsHistoryRepository): GetBetRoomUseCase {
        return GetBetRoomUseCase(betsHistoryRepository)
    }
}