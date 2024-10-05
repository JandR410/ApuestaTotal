package com.example.apuestatotal.presentation.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun SettingsScreen(
    isNotificationEnabled: (Boolean) -> Unit = {},
    isNotificationEnabledValue: Boolean = false,
    isDarkTheme: (Boolean) -> Unit = {},
    isDarkThemeValue: Boolean = false,
    onclick: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Habilitar Notificaciones", modifier = Modifier.weight(1f))
            Switch(
                checked = isNotificationEnabledValue,
                onCheckedChange = { isNotificationEnabled(it) },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xFFD4AF37),
                    uncheckedThumbColor = Color.Gray
                )
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Tema Oscuro", fontSize = 18.sp)
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = isDarkThemeValue,
                onCheckedChange = { isDarkTheme(it) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onclick("home") }) {
            Text("Guardar")
        }
    }
}
