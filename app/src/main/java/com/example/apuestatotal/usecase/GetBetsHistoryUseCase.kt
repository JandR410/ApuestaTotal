package com.example.apuestatotal.usecase

import com.example.apuestatotal.data.model.BetsHistory
import com.example.apuestatotal.domain.repository.DataSource
import javax.inject.Inject

class GetBetsHistoryUseCase @Inject constructor(private val dataSource: DataSource) {
    suspend operator fun invoke(): List<BetsHistory> {
        return dataSource.getBetsHistory()
    }
}