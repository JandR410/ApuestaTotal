package com.example.apuestatotal.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.example.apuestatotal.data.model.Bet

@Composable
fun BetCard(bet: Bet, modifier: Modifier, detail: (Bet) -> Unit) {
    val backgroundColor = when (bet.betNivel) {
        "Leyenda" -> Color.Green
        "King" -> Color.Blue
        "Master" -> Color.Cyan
        "Capo" -> Color.Yellow
        "Cazafijas" -> Color.LightGray
        "Donatelo" -> Color.Red
        else -> Color.White
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { detail(bet) },
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = bet.betSelections.first().eventName,
                style = MaterialTheme.typography.bodyMedium,
                color = if (bet.betNivel != "Capo") White else Black
            )
            Text(
                text = "Nivel: ${bet.betNivel}",
                style = MaterialTheme.typography.bodyLarge,
                color = if (bet.betNivel != "Capo") White else Black
            )
            Text(
                text = "Estado: ${bet.betStatusName}",
                style = MaterialTheme.typography.bodyMedium,
                color = if (bet.betNivel != "Capo") White else Black
            )
            Text(
                text = "Tipo de apuesta: ${bet.betTypeName}",
                style = MaterialTheme.typography.bodyMedium,
                color = if (bet.betNivel != "Capo") White else Black
            )
        }
    }
}