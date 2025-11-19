package com.example.examen_appsmoviles.bonus

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun FibonacciScreen() {
    var inputText by remember { mutableStateOf("") }
    var fibonacciList by remember { mutableStateOf(listOf<Long>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Generador Fibonacci",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = inputText,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    inputText = it
                }
            },
            label = { Text("Ingrese cantidad (N)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val n = inputText.toIntOrNull() ?: 0
                fibonacciList = generateFibonacci(n)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text("Calcular")
        }

        Divider()

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(fibonacciList) { number ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Text(
                        text = number.toString(),
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

fun generateFibonacci(n: Int): List<Long> {
    if (n <= 0) return emptyList()
    if (n == 1) return listOf(0L)

    val list = mutableListOf(0L, 1L)
    while (list.size < n) {
        val next = list[list.lastIndex] + list[list.lastIndex - 1]
        list.add(next)
    }
    return list
}