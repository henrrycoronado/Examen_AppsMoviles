package com.example.examen_appsmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examen_appsmoviles.ui.theme.Examen_AppsMovilesTheme
import com.example.examen_appsmoviles.excersice1.UsersScreen
import com.example.examen_appsmoviles.bonus.FibonacciScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Examen_AppsMovilesTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(navController = navController, startDestination = "menu") {
                            composable("menu") {
                                MenuScreen(
                                    onNavigateToUsers = { navController.navigate("users") },
                                    onNavigateToFibonacci = { navController.navigate("fibonacci") }
                                )
                            }
                            composable("users") {
                                ScreenContainer(onBackClick = { navController.popBackStack() }) {
                                    UsersScreen()
                                }
                            }
                            composable("fibonacci") {
                                ScreenContainer(onBackClick = { navController.popBackStack() }) {
                                    FibonacciScreen()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MenuScreen(
    onNavigateToUsers: () -> Unit,
    onNavigateToFibonacci: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Examen Apps Móviles",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = onNavigateToUsers,
            modifier = Modifier.fillMaxWidth(0.7f).padding(8.dp)
        ) {
            Text("Ver Usuarios (Ejercicio 1)")
        }

        Button(
            onClick = onNavigateToFibonacci,
            modifier = Modifier.fillMaxWidth(0.7f).padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Calcular Fibonacci")
        }
    }
}

@Composable
fun ScreenContainer(
    onBackClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            content()
        }
    }
}