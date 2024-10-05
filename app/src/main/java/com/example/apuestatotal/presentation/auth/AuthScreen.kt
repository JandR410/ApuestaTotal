package com.example.apuestatotal.presentation.auth

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apuestatotal.R

@Composable
fun AuthScreen(
    navigation: (String) -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val transition = updateTransition(targetState = viewModel.isVisible, label = "loginTransition")
    var error by remember { mutableStateOf(false) }
    val event = viewModel.navigateToDashboard.collectAsState().value

    val alpha by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 1000) },
        label = "fadeAlpha"
    ) { state -> if (state) 1f else 0f }

    val scale by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 3000) },
        label = "scaleTransition"
    ) { state -> if (state) 1f else 0.8f }

    LaunchedEffect(Unit) {
        viewModel.showGifAndLoginTransition()
    }

    LaunchedEffect(event) {
        event?.let {
            when (it) {
                "OK" -> {
                    navigation(viewModel.name)
                }

                "ERROR" -> {
                    viewModel.navigateToHome(null)
                    error = true
                }
            }
        }
    }

    if (error) {
        Dialog(
            onDismissRequest = { error = false }) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .padding(top = 30.dp, start = 20.dp, end = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Apuesta Total",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Las credenciales no son las correctas, favor volver a intentar",
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { error = false },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFC090D))
                ) {
                    Text(text = "Reintentar")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .alpha(alpha)
                .scale(scale)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_at),
                contentDescription = "",
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = "Ingresar",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            OutlinedTextField(
                value = viewModel.email,
                onValueChange = { viewModel.onEvent(AuthEvent.EmailChanged(it)) },
                label = { Text("Email") },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { viewModel.onEvent(AuthEvent.PasswordChanged(it)) },
                label = { Text("Password") },
                shape = RoundedCornerShape(12.dp),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Text(
                text = "¿Olvidaste tu contraseña?",
                color = Color.Black
            )
            Button(
                onClick = { viewModel.onEvent(AuthEvent.LoginClicked) },
                modifier = Modifier.fillMaxWidth(),
                enabled = viewModel.email.isNotEmpty() &&viewModel.password.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFC090D))
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.apuesta_total),
                contentDescription = "",
                modifier = Modifier.size(200.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen({})
}