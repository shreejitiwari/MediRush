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
