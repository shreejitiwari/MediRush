package com.example.calleme.EmergencyButton

/*import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calleme.Database.Hospital
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.text.font.FontWeight
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest


val supabase = createSupabaseClient(
    supabaseUrl = "https://cixfdcrvjlfppyyyuufe.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNpeGZkY3J2amxmcHB5eXl1dWZlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDEyOTQ2NjYsImV4cCI6MjA1Njg3MDY2Nn0.S5Ry8z3d7-e_kh4vLRq6k3UAFT3Oa30oSR8Ne86VeVw"
) {
    install(Auth)
    install(Postgrest)
    //install other modules
}

@Composable
fun HospitalFinderScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var hospitals by remember { mutableStateOf<List<Hospital>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()
    var searchJob: Job? by remember { mutableStateOf(null) }

    LaunchedEffect(searchQuery) {
        if (searchQuery.isNotEmpty()) {
            hospitals = SupbaseViewModel.searchHospitals(searchQuery)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(16.dp)
    ) {
        Text(text = "Hospital Finder", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        TopBar()
        SearchBar(searchQuery) { query ->
            searchQuery = query
            searchJob?.cancel()
            searchJob = coroutineScope.launch {
                delay(500)
                hospitals = SupbaseViewModel.searchHospitals(query)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        HospitalList(hospitals)
    }
}

@Composable
fun SearchBar(searchQuery: String, onSearchChange: (String) -> Unit) {
    TextField(
        value = searchQuery,
        onValueChange = { onSearchChange(it) },
        placeholder = { Text("Search by name") },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
    )
}

@Composable
fun HospitalList(hospitals: List<Hospital>) {
    Column {
        hospitals.forEach { hospital ->
            HospitalCard(hospital)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Hospital Finder") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue,
            titleContentColor = Color.White
        )
    )
}

@Composable
fun HospitalCard(hospital: Hospital) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = hospital.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = hospital.address, fontSize = 14.sp)
            Text(text = "Phone: ${hospital.phone}", fontSize = 14.sp)
        }
    }
}*/

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calleme.Database.SupabaseService
import com.example.calleme.Database.SupabaseService.Hospital
import kotlinx.coroutines.launch
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController

@Composable
fun HospitalFinderScreen(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    var hospitals by remember { mutableStateOf(emptyList<Hospital>()) }
    val coroutineScope = rememberCoroutineScope()

    // Fetch hospitals when screen loads
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            hospitals = SupabaseService.getHospitals(searchQuery)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(16.dp)
    ) {
        TopBar(navController)

        SearchBar(searchQuery) { query ->
            searchQuery = query
            coroutineScope.launch {
                hospitals = SupabaseService.getHospitals(query)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display Hospital List
        if (hospitals.isEmpty()) {
            Text(text = "No hospitals found", fontSize = 16.sp, modifier = Modifier.padding(16.dp))
        } else {
            HospitalList(hospitals,navController)
        }
    }
}

@Composable
fun SearchBar(searchQuery: String, onSearchChange: (String) -> Unit) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchChange,
        placeholder = { Text("Search hospital by name") },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { /* Trigger Search */ }),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun HospitalList(hospitals: List<Hospital>, navController: NavHostController) {
    LazyColumn {
        items(hospitals) { hospital ->
            HospitalCard(hospital, navController)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController) {
    TopAppBar(
        title = { Text(text = "Hospital Finder") },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) { // 🔹 Navigate Back
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
}


@Composable
fun HospitalCard(hospital: Hospital, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("hospitalDetails/${hospital.id}")
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = hospital.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = hospital.address, fontSize = 14.sp)
            Text(text = "Phone: ${hospital.phone}", fontSize = 14.sp)
        }
    }
}
