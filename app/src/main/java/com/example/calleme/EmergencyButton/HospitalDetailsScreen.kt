package com.example.calleme.EmergencyButton

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.calleme.Database.SupabaseService
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HospitalDetailsScreen(navController: NavHostController, hospitalId: String) {
    val context = LocalContext.current
    var hospital by remember { mutableStateOf<SupabaseService.Hospital?>(null) }
    val coroutineScope = rememberCoroutineScope()

    // Fetch hospital details
    LaunchedEffect(hospitalId) {
        coroutineScope.launch {
            hospital = SupabaseService.getHospitalById(hospitalId)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Top Bar with Back Button
        TopAppBar(
            title = { Text(text = "Hospital Details") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Blue,
                titleContentColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display hospital details
        hospital?.let { h ->
            Text(text = h.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Address: ${h.address}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Phone: ${h.phone}", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(20.dp))

            // Call Button
            Button(
                onClick = {
                    val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${h.phone}"))
                    context.startActivity(callIntent)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Call Hospital", fontSize = 16.sp)
            }
        } ?: run {
            Text(text = "Loading hospital details...", fontSize = 16.sp)
        }
    }
}
