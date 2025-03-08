package com.example.calleme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController


@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        // Header Section
        HeaderSection()

        // Search Bar
        SearchBar()

        // Buttons Section (Emergency & Book Appointment)
        ActionButtons(navController)

        // Favorite Doctors List
        FavoriteDoctorsSection()

        // Bottom Navigation Bar
        Spacer(modifier = Modifier.weight(2f)) // Push navigation to bottom
        BottomNavigationBar()
    }
}

// Header Section
@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = "Hi Handwerker!", fontSize = 16.sp, color = Color.Gray)
            Text(text = "Eme Call", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
        Image(
            painter = painterResource(id = R.drawable.profile_pic),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
    }
}

// Search Bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Search...") },
        leadingIcon = { Icon(painterResource(R.drawable.search), contentDescription = "Search Icon") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .background(Color.White,shape = RoundedCornerShape(10.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

// Buttons (Emergency & Book Appointment)
@Composable
fun ActionButtons(navController: NavController) {
    Column {
        ButtonCard(
            title = "Emergency",
            color = Color(0xFFFFA8A8),
            icon = R.drawable.alert,
            onClick = {
                navController.navigate("affectedAreafront")
            }
        )
        ButtonCard(
            title = "Book Appointment", color = Color(0xFF74E0A1), icon = R.drawable.search,
            onClick = { navController.navigate("affectedAreafront")}
        )
    }
}

@Composable
fun ButtonCard(title: String, color: Color, icon: Int, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(vertical = 6.dp)
            .clickable { onClick( ) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painterResource(id = icon), contentDescription = title, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            Icon(painterResource(id = R.drawable.arw_right), contentDescription = "Arrow", modifier = Modifier.size(28.dp))
        }
    }
}

// Favorite Doctors Section
@Composable
fun FavoriteDoctorsSection() {
    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Favorite Doctors - Optional", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "See all >", color = Color.Blue, fontSize = 14.sp)
        }
        LazyRow {
            items(listOf(1,2)) { _ ->
                DoctorCard(name = "Dr. Fillerup Grab", specialty = "Medicine Specialist", rating = 4.5)
            }
        }
    }
}

@Composable
fun DoctorCard(name: String, specialty: String, rating: Double) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .width(180.dp)
            .height(220.dp)
            .padding(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painterResource(R.drawable.fakedoc), contentDescription = "Doctor Image", modifier = Modifier.size(120.dp).clip(
                CircleShape))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = specialty, fontSize = 14.sp, color = Color.Gray)
            Text(text = "⭐ $rating", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}

//BottomNavigation Bar
@Composable
fun BottomNavigationBar() {
    var selectedItem by remember { mutableStateOf(0) } // Track selected item index

    NavigationBar(
        containerColor = Color.White, // Background color
        tonalElevation = 8.dp // Elevation
    ) {
        val items = listOf(
            BottomNavItem("Home", R.drawable.home),
            BottomNavItem("Favourite", R.drawable.fav),
            BottomNavItem("Booking", R.drawable.appoint),
            BottomNavItem("Chat", R.drawable.chat)
        )

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = item.label,
                        modifier = Modifier.size(32.dp),
                        tint = if (selectedItem == index) Color.White else Color(0xFF858EA9) // Icon color
                    )
                },
                label = { Text(item.label) },
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White, // Icon color when selected
                    unselectedIconColor = Color(0xFF858EA9), // Icon color when not selected
                    indicatorColor = Color(0xFF0EBE7E) // Background color when selected
                )
            )
        }
    }
}

data class BottomNavItem(val label: String, val icon: Int)
