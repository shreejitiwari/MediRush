package com.example.calleme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calleme.EmergencyButton.AffectedAreaBack
import com.example.calleme.EmergencyButton.AffectedAreaFront
import com.example.calleme.EmergencyButton.FindDoctorsScreen
import com.example.calleme.EmergencyButton.formScreen
import com.example.calleme.EmergencyButton.HospitalFinderScreen
import com.example.calleme.EmergencyButton.HospitalDetailsScreen
import com.example.calleme.UserDataStorage.AffectedAreaScreen
import com.example.calleme.UserDataStorage.ResultScreen


@Composable
fun MainNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "afterSplash",
        modifier = modifier
    ) {
        composable("afterSplash") { ButtonShow(navController) }
        composable("home_screen") { HomeScreen(navController) }
        composable("affectedAreafront") { AffectedAreaFront(navController) }
        composable("affectedAreaBack") { AffectedAreaBack(navController) }
        composable("formScreen") { formScreen(navController) }
        composable("finddoc") { FindDoctorsScreen(navController) }
    }
}


