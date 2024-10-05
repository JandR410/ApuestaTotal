package com.example.apuestatotal.presentation.auth

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apuestatotal.data.room.entity.UserEntity
import com.example.apuestatotal.usecase.GetBetsDetailUseCase
import com.example.apuestatotal.usecase.GetBetsHistoryUseCase
import com.example.apuestatotal.usecase.GetUserRoomUseCase
import com.example.apuestatotal.usecase.GetUserUseCase
import com.example.apuestatotal.usecase.InsertBetDetailUseCase
import com.example.apuestatotal.usecase.InsertBetUseCase
import com.example.apuestatotal.usecase.InsertUserRoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getBetsHistoryUseCase: GetBetsHistoryUseCase,
    private val getBetsDetailUseCase: GetBetsDetailUseCase,
    private val insertUserRoomUseCase: InsertUserRoomUseCase,
    private val insertBetUseCase: InsertBetUseCase,
    private val insertBetDetailUseCase: InsertBetDetailUseCase,
    private val getUserRoomUseCase: GetUserRoomUseCase
) : ViewModel() {

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var showGif by mutableStateOf(true)
        private set
    var isVisible by mutableStateOf(false)
        private set

    private val _navigateToDashboard = MutableStateFlow<String?>(null)
    val navigateToDashboard: StateFlow<String?> get() = _navigateToDashboard

    private val _user = MutableStateFlow<List<UserEntity?>>(emptyList())
    val user: StateFlow<List<UserEntity?>> get() = _user

    var name = ""

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            try {
                val userResult = getUserUseCase()
                insertUserRoomUseCase(userResult)
                val userRoom = getUserRoomUseCase()
                _user.value = userRoom

                val betsHistory = getBetsHistoryUseCase()
                insertBetUseCase(betsHistory)

                val betsDetail = getBetsDetailUseCase()
                insertBetDetailUseCase(betsDetail)

            } catch (e: Exception) {
                Log.e("AuthViewModel", "Error loading bets", e)
            }
        }
    }

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.EmailChanged -> email = event.email
            is AuthEvent.PasswordChanged -> password = event.password
            is AuthEvent.LoginClicked -> performLogin()
        }
    }

    private fun performLogin() {
        viewModelScope.launch {
            var isUserFound = false
            user.value.forEach { user ->
                if (user != null && email == user.correo && password == user.password) {
                    name = user.name
                    isUserFound = true
                    navigateToHome("OK")
                    return@forEach
                }
            }

            if (!isUserFound) {
                navigateToHome("ERROR")
            }
        }
    }

    fun navigateToHome(value: String?) {
        _navigateToDashboard.value = value
    }

    fun showGifAndLoginTransition() {
        viewModelScope.launch {
            delay(2000)
            showGif = false
            isVisible = true
        }
    }
}