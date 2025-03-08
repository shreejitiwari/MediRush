package com.example.calleme.EmergencyButton

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.Log
import androidx.navigation.NavController
import com.example.calleme.R
import com.example.calleme.ui.theme.GreenPrimary
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

@Composable
fun AffectedAreaFront(navController: NavController) {
    val selectedParts = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
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

        Spacer(modifier = Modifier.height(16.dp))

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        ) {
            val boxWidth = maxWidth
            val boxHeight = maxHeight

            Image(
                painter = painterResource(id = R.drawable.front_hbp),
                contentDescription = "Front Body Parts",
                modifier = Modifier.fillMaxSize()
            )

            // Positioning buttons based on the image labels
            BodyPartButton("Head", 0.5f, 0.05f, boxWidth, boxHeight, selectedParts)
            BodyPartButton("Face", 0.5f, 0.12f, boxWidth, boxHeight, selectedParts)
            BodyPartButton("Neck", 0.5f, 0.18f, boxWidth, boxHeight, selectedParts)
            BodyPartButton("Chest", 0.5f, 0.28f, boxWidth, boxHeight, selectedParts)
            BodyPartButton("Left Arm", 0.2f, 0.35f, boxWidth, boxHeight, selectedParts)
            BodyPartButton("Right Arm", 0.8f, 0.35f, boxWidth, boxHeight, selectedParts)
            BodyPartButton("Left Hand", 0.1f, 0.48f, boxWidth, boxHeight, selectedParts)
            BodyPartButton("Right Hand", 0.9f, 0.48f, boxWidth, boxHeight, selectedParts)
            BodyPartButton("Abdominal", 0.5f, 0.42f, boxWidth, boxHeight, selectedParts)
            BodyPartButton("Left Leg", 0.4f, 0.65f, boxWidth, boxHeight, selectedParts)
            BodyPartButton("Right Leg", 0.6f, 0.65f, boxWidth, boxHeight, selectedParts)
            BodyPartButton("Left Knee", 0.4f, 0.75f, boxWidth, boxHeight, selectedParts)
            BodyPartButton("Right Knee", 0.6f, 0.75f, boxWidth, boxHeight, selectedParts)
            BodyPartButton("Left Foot", 0.4f, 0.9f, boxWidth, boxHeight, selectedParts)
            BodyPartButton("Right Foot", 0.6f, 0.9f, boxWidth, boxHeight, selectedParts)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.5f))
                .padding(10.dp)
        ) {
            Text(
                text = "Selected: ${selectedParts.joinToString(", ")}",
                color = Color.White
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("affectedAreaBack") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)
        ) {
            Text("Next")
        }
    }
}

@Composable
fun BodyPartButton(
    name: String,
    xFactor: Float,
    yFactor: Float,
    boxWidth: Dp,
    boxHeight: Dp,
    selectedParts: MutableList<String>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .absoluteOffset(
                x = (boxWidth * xFactor) - 40.dp,
                y = (boxHeight * yFactor) - 15.dp
            )
    ) {
        Button(
            onClick = {
                if (name !in selectedParts) selectedParts.add(name)
                else selectedParts.remove(name)
            },
            modifier = Modifier.size(80.dp, 30.dp)
        ) {
            Text(name, fontSize = 10.sp)
        }
    }
}