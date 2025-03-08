package com.example.calleme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calleme.EmergencyButton.AffectedAreaBack
import com.example.calleme.EmergencyButton.AffectedAreasScreen
import com.example.calleme.EmergencyButton.formScreen
import com.example.calleme.EmergencyButton.HospitalFinderScreen
import com.example.calleme.EmergencyButton.HospitalDetailsScreen


@Composable
fun MainNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "afterSplash",
        modifier = modifier
    ) {
        composable("afterSplash") { ButtonShow(navController) }
        composable("home_screen") { HomeScreen(navController) }
        composable("affectedAreafront") { AffectedAreasScreen(navController) }
        composable("affectedAreaBack") { AffectedAreaBack(navController) }
        composable("formScreen") { formScreen(navController) }
        composable("findHospitals") { HospitalFinderScreen(navController) }
        composable("hospitalDetails/{hospitalId}") { backStackEntry ->
            val hospitalId = backStackEntry.arguments?.getString("hospitalId") ?: ""
            HospitalDetailsScreen(navController, hospitalId)
        }
    }
}
