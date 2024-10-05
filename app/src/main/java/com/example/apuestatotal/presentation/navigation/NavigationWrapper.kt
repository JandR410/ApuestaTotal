package com.example.apuestatotal.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apuestatotal.component.AppDrawer
import com.example.apuestatotal.component.Scaffold
import com.example.apuestatotal.data.model.Bet
import com.example.apuestatotal.data.model.BetSelection
import com.example.apuestatotal.data.model.User
import com.example.apuestatotal.presentation.auth.AuthScreen
import com.example.apuestatotal.presentation.auth.AuthViewModel
import com.example.apuestatotal.presentation.betdetail.BetDetailScreen
import com.example.apuestatotal.presentation.home.HomeScreen
import com.example.apuestatotal.presentation.home.HomeViewModel
import com.example.apuestatotal.presentation.setting.SettingsScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    val dummySelections = listOf(
        BetSelection(
            selectionId = 1L,
            selectionStatus = 1,
            price = "1.5",
            name = "Equipo A vs Equipo B",
            spec = "Especificación",
            marketTypeId = 1,
            marketId = 1,
            marketName = "Market A",
            isLive = false,
            isBetBuilder = false,
            isBanker = false,
            isVirtual = false,
            bbSelections = null,
            eventId = 1L,
            eventCode = "ABC",
            feedEventId = 12345L,
            eventName = "Evento Futbol",
            sportTypeId = 1,
            categoryId = 1,
            categoryName = "Fútbol",
            champId = 1,
            champName = "Liga 1",
            eventScore = "2-1",
            gameTime = "90'",
            eventDate = "2024-10-05",
            pitcherInfo = null,
            runners = null,
            extraEventInfo = null,
            rc = false,
            liveInfoAtEventMinute = null,
            isLiveOrVirtual = false,
            earlyPayout = false,
            boreDraw = false,
            deadHeatFactor = null,
            dbId = 1
        )
    )

    val dummyBet = Bet(
        betNivel = "Nivel 1",
        betStarts = 10,
        betStatusName = "Ganado",
        betTypeName = "Directa",
        bgSrc = "",
        cashoutOdds = "2.5",
        totalOdds = "3.0",
        totalStake = "100",
        totalWin = "300",
        cashoutValue = "200",
        createdDate = "2024-10-05",
        betSelections = dummySelections,
        betStatus = 1,
        betType = 1,
        betId = 12345L
    )
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            val viewModel: AuthViewModel = hiltViewModel()
            AuthScreen(
                navigation = {
                    navController.navigate("home/$it")
                },
                viewModel = viewModel
            )
        }
        composable("home/{name}") { backStackEntry ->
            val viewModel: HomeViewModel = hiltViewModel()
            val name = backStackEntry.arguments?.getString("name") ?: ""

            val navigation = remember { mutableStateOf("home") }
            val isNotificationEnabledValue = remember { mutableStateOf(false) }
            val isDarkThemeValue = remember { mutableStateOf(false) }
            val navigationDetail = remember { mutableStateOf(dummyBet) }
            var currentScreen by remember { mutableIntStateOf(0) }
            Scaffold(
                selection = currentScreen,
                onSelectScreen = { screenName ->
                    when (screenName) {
                        "home" -> {
                            currentScreen = 0
                            navigation.value = "home"
                        }

                        "details" -> {
                            currentScreen = 1
                            navigation.value = "details"
                        }

                        "setting" -> {
                            currentScreen = 2
                            navigation.value = "setting"
                        }
                    }
                },
                drawerContent = {
                    AppDrawer(
                        user = User(name, "", ""),
                        modifier = Modifier,
                        onNavigation = { navigation.value = it },
                        onMenuItemClick = { navController.navigate("login") }
                    )
                }
            ) {
                when (navigation.value) {
                    "home" -> HomeScreen(
                        viewModel = viewModel,
                        navigateToDetail = { bet ->
                            navigationDetail.value = bet
                            navigation.value = "details"
                            currentScreen = 1
                        }
                    )

                    "details" -> BetDetailScreen(
                        bet = navigationDetail.value
                    )

                    "setting" -> {
                        SettingsScreen(
                            isNotificationEnabled = {
                                isNotificationEnabledValue.value = !isNotificationEnabledValue.value
                            },
                            isNotificationEnabledValue = isNotificationEnabledValue.value,
                            isDarkTheme = { isDarkThemeValue.value = !isDarkThemeValue.value },
                            isDarkThemeValue = isDarkThemeValue.value,
                            onclick = {
                                navigation.value = "home"
                                currentScreen = 0
                            }
                        )
                    }
                }
            }

        }
    }
}