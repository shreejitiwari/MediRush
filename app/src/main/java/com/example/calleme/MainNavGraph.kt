package com.example.calleme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calleme.Database.FetchDoctorsScreen
import com.example.calleme.EmergencyButton.AffectedAreaBack
import com.example.calleme.EmergencyButton.AffectedAreaFront
import com.example.calleme.EmergencyButton.FormScreen
import com.example.calleme.EmergencyButton.HospitalFinderScreen



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
        composable("formScreen") { FormScreen(navController) }
        //composable("findHospitals") { HospitalFinderScreen(navController) }
        composable("fetch") { FetchDoctorsScreen(navController) }
    }
}
