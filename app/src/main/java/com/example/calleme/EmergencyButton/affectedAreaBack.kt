/*package com.example.calleme.EmergencyButton

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calleme.R
import com.example.calleme.ui.theme.GreenPrimary

@Composable
fun AffectedAreaBack(navController: NavController) {
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

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.back_body_part), // Back Body Image
            contentDescription = "Back Body Parts",
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clickable {
                    // Handle body part selection
                }
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { navController.navigate("formScreen") }, // Navigate to form
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)
        ) {
            Text("Next")
        }
    }
}*/
package com.example.calleme.EmergencyButton

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calleme.R
import com.example.calleme.ui.theme.GreenPrimary
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun AffectedAreaBack(navController: NavController) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var imageSize by remember { mutableStateOf(IntSize.Zero) }
    var selectedPoints by remember { mutableStateOf<List<Offset>>(emptyList()) }

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

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale *= zoom
                        offset = Offset(offset.x + pan.x, offset.y + pan.y)
                    }
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_body_part),
                contentDescription = "Back Body Parts",
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offset.x,
                        translationY = offset.y
                    )
                    .onGloballyPositioned { imageSize = it.size }
                    .pointerInput(Unit) {
                        detectTapGestures { tapOffset ->
                            val relativeX = tapOffset.x / imageSize.width
                            val relativeY = tapOffset.y / imageSize.height
                            selectedPoints = selectedPoints + Offset(relativeX, relativeY)
                        }
                    }
            )

            selectedPoints.forEach { point ->
                Box(
                    modifier = Modifier
                        .offset(
                            x = (point.x * imageSize.width).dp,
                            y = (point.y * imageSize.height).dp
                        )
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(Color.Red.copy(alpha = 0.6f))
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { navController.navigate("formScreen") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)
        ) {
            Text("Next")
        }
    }
}

