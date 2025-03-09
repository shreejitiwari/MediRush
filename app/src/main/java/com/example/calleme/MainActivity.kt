package com.example.calleme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calleme.ui.theme.GradientBackground // Import your theme
import com.example.calleme.ui.theme.CallEmeTheme
import androidx.navigation.compose.rememberNavController
import com.example.calleme.EmergencyButton.AffectedAreaFront
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest


val supabase = createSupabaseClient(
    supabaseUrl = "https://cixfdcrvjlfppyyyuufe.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNpeGZkY3J2amxmcHB5eXl1dWZlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDEyOTQ2NjYsImV4cCI6MjA1Njg3MDY2Nn0.S5Ry8z3d7-e_kh4vLRq6k3UAFT3Oa30oSR8Ne86VeVw"
) {
    install(Postgrest)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            CallEmeTheme {
                Scaffold { contentPadding ->
                    GradientBackground {
                        MainNavGraph(
                            navController = navController,
                            modifier = Modifier.padding(contentPadding))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CallEmeTheme {
        GradientBackground {
            HomeScreen(navController = rememberNavController())
        }
    }
}


