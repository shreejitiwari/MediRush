/*package com.example.calleme.EmergencyButton

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.navigation.NavController
import com.example.calleme.R
import com.example.calleme.ui.theme.GreenPrimary
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun AffectedAreasScreen(navController: NavController) {
    var selectedBodyParts by remember { mutableStateOf(mutableListOf<String>()) }
    var scale by remember { mutableStateOf(1f) } // Zoom level

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(painter = painterResource(id = R.drawable.back), contentDescription = "Back")
                }
                Text(
                    text = "Choose Affected Areas",
                    fontSize = 21.sp,
                    modifier = Modifier.padding(18.dp)
                )
            }
        }

        // Zoomable Image Inside Box (No Movement)
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .pointerInput(Unit) {
                        detectTransformGestures { _, _, zoom, _ ->
                            scale = (scale * zoom).coerceIn(1f, 3f) // Limit zoom between 1x and 3x
                        }
                    }
                    .pointerInput(Unit) {
                        detectTapGestures { offset ->
                            val adjustedOffset = Offset(
                                x = offset.x / scale, // Adjust touch position based on zoom
                                y = offset.y / scale
                            )
                            val part = getBodyPartName(adjustedOffset)
                            if (part.isNotEmpty() && !selectedBodyParts.contains(part)) {
                                selectedBodyParts = selectedBodyParts.toMutableList().apply { add(part) }
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.front_hbp),
                    contentDescription = "Body Parts",
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer(scaleX = scale, scaleY = scale)
                )
            }
        }

        // Selected Body Parts
        item {
            Text("Selected Areas:", fontSize = 18.sp, color = GreenPrimary)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                if (selectedBodyParts.isEmpty()) {
                    Text("No selection", fontSize = 16.sp, color = Color.Gray)
                } else {
                    LazyColumn {
                        items(selectedBodyParts) { part ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp),
                                colors = CardDefaults.cardColors(containerColor = GreenPrimary.copy(alpha = 0.2f))
                            ) {
                                Text(
                                    text = part,
                                    modifier = Modifier.padding(8.dp),
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        // Buttons (Clear & Next)
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Button(
                    onClick = { selectedBodyParts = mutableListOf() }, // Clear selection
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) {
                    Text("Clear")
                }

                Button(
                    onClick = { navController.navigate("AffectedAreaBack") },
                    colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)
                ) {
                    Text("Next")
                }
            }
        }
    }
}

/**
 * Map touch position to body part names (Handles Zoom)
 */
fun getBodyPartName(offset: Offset): String {
    return when {
        offset.y < 80 -> "Head"
        offset.y in 80f..150f -> "Face"
        offset.y in 150f..220f && offset.x < 160f -> "Left Shoulder"
        offset.y in 150f..220f && offset.x > 240f -> "Right Shoulder"
        offset.y in 150f..250f -> "Chest"
        offset.y in 250f..300f -> "Upper Abdomen"
        offset.y in 300f..370f -> "Lower Abdomen"
        offset.y in 220f..400f && offset.x < 100f -> "Left Arm"
        offset.y in 220f..400f && offset.x > 300f -> "Right Arm"
        offset.y in 400f..500f && offset.x < 80f -> "Left Hand"
        offset.y in 400f..500f && offset.x > 320f -> "Right Hand"
        offset.y in 370f..480f && offset.x in 100f..200f -> "Left Thigh"
        offset.y in 370f..480f && offset.x in 200f..300f -> "Right Thigh"
        offset.y in 480f..600f && offset.x in 100f..200f -> "Left Knee"
        offset.y in 480f..600f && offset.x in 200f..300f -> "Right Knee"
        offset.y > 600f && offset.x in 100f..200f -> "Left Foot"
        offset.y > 600f && offset.x in 200f..300f -> "Right Foot"
        else -> ""
    }
}*/
/*package com.example.calleme.EmergencyButton

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.NavController
import com.example.calleme.R
import com.example.calleme.ui.theme.GreenPrimary
import androidx.compose.foundation.gestures.detectTransformGestures


@Composable
fun AffectedAreasScreen(navController: NavController) {
    var selectedBodyParts by remember { mutableStateOf(mutableListOf<String>()) }
    var scale by remember { mutableStateOf(1f) }
    var zoomCenter by remember { mutableStateOf(Offset.Zero) }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(painter = painterResource(id = R.drawable.back), contentDescription = "Back")
                }
                Text(
                    text = "Choose Affected Areas",
                    fontSize = 21.sp,
                    modifier = Modifier.padding(18.dp)
                )
            }
        }

        // Zoomable Image with Pinch Zoom to Particular Body Part
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .pointerInput(Unit) {
                        detectTransformGestures { centroid, pan, zoom, _ ->
                            scale *= zoom
                            zoomCenter = centroid
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.front_hbp),
                    contentDescription = "Body Parts",
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            translationX = -zoomCenter.x * (scale - 1),
                            translationY = -zoomCenter.y * (scale - 1)
                        )
                        .pointerInput(Unit) {
                            detectTapGestures { offset ->
                                val part = getBodyPartName(offset)
                                if (part.isNotEmpty() && !selectedBodyParts.contains(part)) {
                                    selectedBodyParts = selectedBodyParts.toMutableList().apply { add(part) }
                                }
                            }
                        }
                )
            }
        }

        // Selected Body Parts
        item {
            Text("Selected Areas:", fontSize = 18.sp, color = GreenPrimary)
            Box(
                modifier = Modifier.fillMaxWidth().height(150.dp).padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                if (selectedBodyParts.isEmpty()) {
                    Text("No selection", fontSize = 16.sp, color = Color.Gray)
                } else {
                    LazyColumn {
                        items(selectedBodyParts) { part ->
                            Card(
                                modifier = Modifier.fillMaxWidth().padding(4.dp),
                                colors = CardDefaults.cardColors(containerColor = GreenPrimary.copy(alpha = 0.2f))
                            ) {
                                Text(
                                    text = part,
                                    modifier = Modifier.padding(8.dp),
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        // Buttons (Clear & Next)
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Button(
                    onClick = {
                        selectedBodyParts = mutableListOf()
                        scale = 1f // Reset zoom on clear
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) {
                    Text("Clear")
                }

                Button(
                    onClick = { navController.navigate("AffectedAreaBack") },
                    colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)
                ) {
                    Text("Next")
                }
            }
        }
    }
}

fun getBodyPartName(offset: Offset): String {
    return when {
        offset.y < 80 -> "Head"
        offset.y in 80f..150f -> "Face"
        offset.y in 150f..220f && offset.x < 160f -> "Left Shoulder"
        offset.y in 150f..220f && offset.x > 240f -> "Right Shoulder"
        offset.y in 150f..250f -> "Chest"
        offset.y in 250f..300f -> "Upper Abdomen"
        offset.y in 300f..370f -> "Lower Abdomen"
        offset.y in 220f..400f && offset.x < 100f -> "Left Arm"
        offset.y in 220f..400f && offset.x > 300f -> "Right Arm"
        offset.y in 400f..500f && offset.x < 80f -> "Left Hand"
        offset.y in 400f..500f && offset.x > 320f -> "Right Hand"
        offset.y in 370f..480f && offset.x in 100f..200f -> "Left Thigh"
        offset.y in 370f..480f && offset.x in 200f..300f -> "Right Thigh"
        offset.y in 480f..600f && offset.x in 100f..200f -> "Left Knee"
        offset.y in 480f..600f && offset.x in 200f..300f -> "Right Knee"
        offset.y > 600f && offset.x in 100f..200f -> "Left Foot"
        offset.y > 600f && offset.x in 200f..300f -> "Right Foot"
        else -> ""
    }
}*/
package com.example.calleme.EmergencyButton

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.RectF
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calleme.R
import com.example.calleme.ui.theme.GreenPrimary
import android.graphics.RectF

@Composable
fun AffectedAreasScreen(navController: NavController) {
    var selectedBodyParts by remember { mutableStateOf(mutableListOf<String>()) }
    var scale by remember { mutableStateOf(1f) }
    var zoomCenter by remember { mutableStateOf(Offset.Zero) }
    var currentView by remember { mutableStateOf("Front") } // Toggle for front/back view

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "Back"
                    )
                }
                Text(
                    text = "Choose Affected Areas",
                    fontSize = 21.sp,
                    modifier = Modifier.padding(18.dp)
                )
            }
        }

        // Toggle for Front/Back View
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { currentView = "Front" }) {
                    Text(text = "Front View")
                }
                Button(onClick = { currentView = "Back" }) {
                    Text(text = "Back View")
                }
            }
        }

        // Zoomable Image with Pinch-Zoom and Accurate Tap Handling
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .pointerInput(Unit) {
                        detectTransformGestures { centroid, pan, zoom, _ ->
                            scale *= zoom
                            zoomCenter = centroid
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                val imageRes = if (currentView == "Front") R.drawable.front_hbp else R.drawable.back_body_part
                    Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Body Parts",
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            translationX = -zoomCenter.x * (scale - 1),
                            translationY = -zoomCenter.y * (scale - 1)
                        )
                        .pointerInput(Unit) {
                            detectTapGestures { offset ->
                                val normalizedX = (offset.x / scale) + zoomCenter.x
                                val normalizedY = (offset.y / scale) + zoomCenter.y
                                val part = getBodyPartName(normalizedX, normalizedY, currentView)
                                if (part.isNotEmpty() && !selectedBodyParts.contains(part)) {
                                    selectedBodyParts = selectedBodyParts.toMutableList().apply { add(part) }
                                }
                            }
                        }
                )
            }
        }

        // Selected Body Parts
        item {
            Text("Selected Areas:", fontSize = 18.sp, color = GreenPrimary)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                if (selectedBodyParts.isEmpty()) {
                    Text("No selection", fontSize = 16.sp, color = Color.Gray)
                } else {
                    LazyColumn {
                        items(selectedBodyParts) { part ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp),
                                colors = CardDefaults.cardColors(containerColor = GreenPrimary.copy(alpha = 0.2f))
                            ) {
                                Text(
                                    text = part,
                                    modifier = Modifier.padding(8.dp),
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        // Buttons (Clear & Next)
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Button(
                    onClick = {
                        selectedBodyParts = mutableListOf()
                        scale = 1f // Reset zoom
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) {
                    Text("Clear")
                }

                Button(
                    onClick = { navController.navigate("AffectedAreaBack") },
                    colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)
                ) {
                    Text("Next")
                }
            }
        }
    }
}

// Function to Map Tapped Coordinates to Body Parts
fun getBodyPartName(x: Float, y: Float, view: String): String {
    // Example for front view
    val bodyPartMapFront = mapOf(
        Pair("Head", RectF(0f, 0f, 100f, 80f)),
        Pair("Chest", RectF(50f, 150f, 150f, 250f))
        // Add more parts with exact coordinates
    )
    // Example for back view
    val bodyPartMapBack = mapOf(
        Pair("Back Head", RectF(0f, 0f, 100f, 80f)),
        Pair("Back Chest", RectF(50f, 150f, 150f, 250f))
        // Add more parts
    )
    val mapToUse = if (view == "Front") bodyPartMapFront else bodyPartMapBack
    return mapToUse.entries.find { it.value.contains(x, y) }?.key.orEmpty()
}
