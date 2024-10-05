package com.example.apuestatotal.presentation.betdetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apuestatotal.R
import com.example.apuestatotal.data.model.Bet
import com.example.apuestatotal.data.model.BetSelection

@Composable
fun BetDetailScreen(bet: Bet) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(900)) + slideInVertically(
            initialOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(900)
        ),
        exit = fadeOut(animationSpec = tween(900)) + slideOutVertically(
            targetOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(900)
        )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                BetOverview(bet = bet)
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(bet.betSelections) { selection ->
                BetSelectionCard(selection = selection)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun BetOverview(bet: Bet) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (bet.bgSrc.isNotEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.banner),
                contentDescription = "Banner",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Apuesta ID: ${bet.betId}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )

        Text(text = "Nivel: ${bet.betNivel}", fontSize = 16.sp)
        Text(text = "Estado: ${bet.betStatusName}", fontSize = 16.sp)
        Text(text = "Tipo de Apuesta: ${bet.betTypeName}", fontSize = 16.sp)
        Text(text = "Fecha: ${bet.createdDate}", fontSize = 16.sp)
        Text(text = "Cuotas Totales: ${bet.totalOdds}", fontSize = 16.sp)
        Text(text = "Valor Cashout: ${bet.cashoutValue}", fontSize = 16.sp)
        Text(text = "Posibles Ganancias: ${bet.totalWin}", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun BetSelectionCard(selection: BetSelection) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Selección: ${selection.name}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Text(text = "Estado: ${selection.selectionStatus}", fontSize = 16.sp)
            Text(text = "Precio: ${selection.price}", fontSize = 16.sp)
            Text(text = "Evento: ${selection.eventName}", fontSize = 16.sp)
            Text(text = "Fecha del Evento: ${selection.eventDate}", fontSize = 16.sp)

            selection.spec?.let {
                Text(text = "Especificación: $it", fontSize = 14.sp, color = Color.Gray)
            }
            Text(text = "Deporte: ${selection.sportTypeId}", fontSize = 14.sp, color = Color.Gray)
            Text(
                text = "Categoría: ${selection.categoryName}",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BetDetailScreenPreview() {
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

    BetDetailScreen(bet = dummyBet)
}
