package com.example.apuestatotal.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apuestatotal.data.model.Bet
import com.example.apuestatotal.data.model.BetsHistory
import com.example.apuestatotal.usecase.GetBetsDetailUseCase
import com.example.apuestatotal.usecase.GetBetsHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBetsHistoryUseCase: GetBetsHistoryUseCase,
    private val getBetsDetailUseCase: GetBetsDetailUseCase
) : ViewModel() {

    private val _getBet = MutableStateFlow<List<Bet>>(emptyList())
    val getBet: StateFlow<List<Bet>> get() = _getBet

    private val _getBetHistory = MutableStateFlow<List<BetsHistory>>(emptyList())
    val getBetHistory: StateFlow<List<BetsHistory>> get() = _getBetHistory

    init {
        loadBeats()
        loadBeatsHistory()
    }

    private fun loadBeats() {
        viewModelScope.launch {
            try {
                val betsDetailResult = getBetsDetailUseCase()
                _getBet.value = betsDetailResult
                Log.d("HomeViewModel", "Bets loaded: $betsDetailResult")
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error loading bets", e)
            }
        }
    }

    private fun loadBeatsHistory() {
        viewModelScope.launch {
            try {
                val betsHistoryResult = getBetsHistoryUseCase()
                _getBetHistory.value = betsHistoryResult
                Log.d("HomeViewModel", "Bets loaded: $betsHistoryResult")
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error loading bets", e)
            }
        }
    }

}