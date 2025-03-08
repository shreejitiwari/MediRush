package com.example.calleme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ButtonShow(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonCard(
            title = "Emergency",
            color = Color(0xFFFFA8A8),
            icon = R.drawable.alert,
            onClick = {
                navController.navigate("home_screen")
            }
        )
        Spacer(modifier = Modifier.height(50.dp))
        ButtonCard(
            title = "Book Appointment", color = Color(0xFF74E0A1), icon = R.drawable.search,
            onClick = { navController.navigate("affectedAreafront")}
        )
    }
}