package com.example.calleme.EmergencyButton

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calleme.R
import com.example.calleme.ui.theme.GreenPrimary
import io.ktor.client.engine.callContext
//import io.ktor.util.pipeline.StackWalkingFailedFrame.context
import kotlin.contracts.contract
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context
//import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions


@Composable
fun FindDoctorsScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFFFFFFF))) {
        TopBar()
        SearchBar()
        DoctorList()
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* Handle Back Navigation */ }) {
            Icon(painterResource(id = R.drawable.light_back), contentDescription = "Back")
        }
        Text("Find Doctors", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SearchBar() {
    TextField(
        value = "Dentist",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        placeholder = { Text("Search doctors...") },
        leadingIcon = { Icon(painterResource(id = R.drawable.search), contentDescription = "Search") }
    )
}

@Composable
fun DoctorList() {
    Column(modifier = Modifier.fillMaxSize()) {
        val doctors = listOf(
            Doctor("Dr. Shruti Kedia", "Tooths Dentist", 7, 87, 69, "10:00 AM"),
            Doctor("Dr. Watamaniuk", "Tooths Dentist", 9, 74, 78, "12:00 AM"),
            Doctor("Dr. Crownover", "Tooths Dentist", 5, 59, 86, "11:00 AM"),
            Doctor("Dr. Balestra", "Tooths Dentist", 6, 87, 69, "10:00 AM")
        )
        doctors.forEach { doctor ->
            DoctorCard(doctor)
        }
    }
}

@Composable
fun DoctorCard(doctor: Doctor) {
    var isFavorite by remember { mutableStateOf(false) }
    val context = LocalContext.current // Get the context
    Card(
        shape = RoundedCornerShape(12.dp),
        //elevation = 4.dp,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = R.drawable.doc1),
                contentDescription = "Doctor Image",
                modifier = Modifier.size(60.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(doctor.name, fontWeight = FontWeight.Bold)
                Text(doctor.specialization, color = Color.Gray, fontSize = 14.sp)
                Text("${doctor.experience} Years experience", fontSize = 12.sp, color = Color.Gray)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("⭐ ${doctor.rating}%", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("${doctor.reviews} Patient Stories", fontSize = 12.sp, color = Color.Gray)
                }
                Text("Next Available: ${doctor.availableTime} tomorrow", fontSize = 12.sp, color = Color.Green)
            }
            /*IconButton(onClick = { isFavorite = !isFavorite }) {
                val isFavorite = false
                Icon(
                    painter = painterResource(id = if (isFavorite) R.drawable.redfav else R.drawable.ic_favorite_border),
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* Handle Booking */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)
            ) {
                Text("Book Now", color = Color.White, fontWeight = FontWeight.Bold)
            }*/
            IconButton(onClick = { isFavorite = !isFavorite }) {
                Icon(
                    painter = painterResource(id = if (isFavorite) R.drawable.redfav else R.drawable.ic_favorite_border),
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                Toast.makeText(context, "Booking Successful", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(8.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)
        ) {
            Text("Book Now", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
    }
    }

data class Doctor(
    val name: String,
    val specialization: String,
    val experience: Int,
    val rating: Int,
    val reviews: Int,
    val availableTime: String
)

