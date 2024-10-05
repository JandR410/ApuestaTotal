package com.example.apuestatotal.presentation.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.apuestatotal.component.AppDropdownTextField
import com.example.apuestatotal.component.BetCard
import com.example.apuestatotal.data.model.Bet
import com.example.apuestatotal.data.model.BetsHistory

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigateToDetail: (Bet) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var searchCriteria by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(false) }

    val bets = viewModel.getBet.collectAsState(initial = emptyList()).value
    val betDetails = viewModel.getBetHistory.collectAsState(initial = emptyList()).value

    val filteredBets = filterBetsByGame(bets, betDetails).filter { bet ->
        if (searchCriteria == "Evento") {
            bet.betSelections.any {
                it.eventName.contains(
                    searchQuery,
                    ignoreCase = true
                )
            }
        } else {
            if (searchCriteria == "Tipo") {
                bet.betTypeName.contains(searchQuery, ignoreCase = true)
            } else {
                bet.betStatusName.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    LaunchedEffect(Unit) {
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + scaleIn(initialScale = 0.8f, animationSpec = tween(900)),
        exit = fadeOut() + scaleOut(targetScale = 0.8f, animationSpec = tween(900))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                SearchCriteriaSelector { criteria ->
                    searchCriteria = criteria
                }

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                    },
                    label = { Text("Buscar") },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(filteredBets) { bet ->
                        BetCard(
                            bet = bet,
                            modifier = Modifier.padding(8.dp),
                            detail = {
                                navigateToDetail(it)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchCriteriaSelector(onCriteriaChange: (String) -> Unit) {
    var selectedOption by remember { mutableStateOf("") }
    val options = listOf("Estado", "Evento", "Tipo")

    AppDropdownTextField(
        selectedOption = selectedOption,
        options = options,
        onOptionSelected = {
            selectedOption = it
            onCriteriaChange(it)
        },
        labelText = "Criterios de filtro"
    )
}

fun filterBetsByGame(bets: List<Bet>, betDetails: List<BetsHistory>): List<Bet> {
    val gameIds = betDetails.map { it.game }
    return bets.filter { bet ->
        gameIds.contains(bet.betId.toString())
    }
}