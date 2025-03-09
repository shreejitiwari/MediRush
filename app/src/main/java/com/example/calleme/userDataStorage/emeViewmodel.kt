package com.example.calleme.UserDataStorage

import androidx.compose.foundation.Image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.calleme.R
import com.example.calleme.ui.theme.GreenPrimary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


class EmergencyViewModel : ViewModel() {
    private val _problemDescription = MutableStateFlow("")
    val problemDescription: StateFlow<String> = _problemDescription

    private val _date = MutableStateFlow("")
    val date: StateFlow<String> = _date

    private val _time = MutableStateFlow("")
    val time: StateFlow<String> = _time

    private val _location = MutableStateFlow("")
    val location: StateFlow<String> = _location

    private val _audioFilePath = MutableStateFlow("")
    val audioFilePath: StateFlow<String> = _audioFilePath

    private val _attachedFiles = MutableStateFlow<List<String>>(emptyList())
    val attachedFiles: StateFlow<List<String>> = _attachedFiles

    fun updateProblemDescription(description: String) {
        _problemDescription.value = description
    }

    fun updateDate(date: String) {
        _date.value = date
    }

    fun updateTime(time: String) {
        _time.value = time
    }

    fun updateLocation(location: String) {
        _location.value = location
    }

    fun updateAudioFilePath(filePath: String) {
        _audioFilePath.value = filePath
    }

    fun updateAttachedFiles(files: List<String>) {
        _attachedFiles.value = files
    }

    fun removeFile(index: Int) {
        _attachedFiles.value = _attachedFiles.value.toMutableList().apply { removeAt(index) }
    }

    private val _selectedBodyParts = MutableStateFlow<List<String>>(emptyList())
    val selectedBodyParts: StateFlow<List<String>> = _selectedBodyParts
    fun updateSelectedParts(part: String) {
        _selectedBodyParts.value = if (part in _selectedBodyParts.value) {
            _selectedBodyParts.value - part // Remove if already selected
        } else {
            _selectedBodyParts.value + part // Add if not selected
        }
    }

    private val _selectedParts = mutableStateListOf<String>()
    val selectedParts: List<String> get() = _selectedParts

    fun toggleBodyPart(name: String) {
        if (_selectedParts.contains(name)) {
            _selectedParts.remove(name)
        } else {
            _selectedParts.add(name)
        }
    }
}

@Composable
fun AffectedAreaScreen(navController: NavController, viewModel: EmergencyViewModel, isFront: Boolean) {
    val selectedParts by viewModel.selectedBodyParts.collectAsState()

    val bodyImage = if (isFront) R.drawable.front_hbp else R.drawable.back_body_part
    val bodyParts = if (isFront) listOf(
        "Head" to (0.5f to 0.05f), "Face" to (0.5f to 0.12f), "Neck" to (0.5f to 0.18f),
        "Chest" to (0.5f to 0.28f), "Left Arm" to (0.2f to 0.35f), "Right Arm" to (0.8f to 0.35f),
        "Left Hand" to (0.1f to 0.48f), "Right Hand" to (0.9f to 0.48f), "Abdominal" to (0.5f to 0.42f),
        "Left Leg" to (0.4f to 0.65f), "Right Leg" to (0.6f to 0.65f), "Left Knee" to (0.4f to 0.75f),
        "Right Knee" to (0.6f to 0.75f), "Left Foot" to (0.4f to 0.9f), "Right Foot" to (0.6f to 0.9f)
    ) else listOf(
        "Head" to (0.5f to 0.05f), "Neck" to (0.5f to 0.12f), "Shoulders" to (0.5f to 0.18f),
        "Back" to (0.5f to 0.28f), "Left Arm" to (0.2f to 0.35f), "Right Arm" to (0.8f to 0.35f),
        "Buttocks" to (0.5f to 0.48f), "Left Leg" to (0.4f to 0.65f), "Right Leg" to (0.6f to 0.65f),
        "Left Foot" to (0.4f to 0.9f), "Right Foot" to (0.6f to 0.9f)
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(painter = painterResource(id = R.drawable.back), contentDescription = "Back")
            }
            Text("Choose Affected Areas", fontSize = 21.sp, modifier = Modifier.padding(18.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        BoxWithConstraints(modifier = Modifier.fillMaxWidth().height(500.dp)) {
            val boxWidth = maxWidth
            val boxHeight = maxHeight

            Image(painter = painterResource(id = bodyImage), contentDescription = "Body Parts", modifier = Modifier.fillMaxSize())

            bodyParts.forEach { (name, position) ->
                BodyPartButton(name, position.first, position.second, boxWidth, boxHeight, selectedParts, viewModel)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth().background(Color.Black.copy(alpha = 0.5f)).padding(10.dp)
        ) {
            Text("Selected: ${selectedParts.joinToString(", ")}", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate(if (isFront) "affectedAreaBack" else "FormScreen") },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)
        ) {
            Text("Next")
        }
    }
}

/*@Composable
fun BodyPartButton(name: String, xFactor: Float, yFactor: Float, boxWidth: Dp, boxHeight: Dp, selectedParts: List<String>, viewModel: EmergencyViewModel) {
    Box(modifier = Modifier.fillMaxSize().absoluteOffset(x = (boxWidth * xFactor) - 40.dp, y = (boxHeight * yFactor) - 15.dp)) {
        Button(
            onClick = { viewModel.updateSelectedParts(name) },
            modifier = Modifier.size(80.dp, 30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (name in selectedParts) Color.Green else Color.Black.copy(alpha = 0.2f),
                contentColor = Color.White
            )
        ) {
            Text(name, fontSize = 10.sp)
        }
    }
}*/
@Composable
fun BodyPartButton(name: String, xFactor: Float, yFactor: Float, boxWidth: Dp, boxHeight: Dp, selectedParts: List<String>, viewModel: EmergencyViewModel) {
    Box(modifier = Modifier.fillMaxSize().absoluteOffset(x = (boxWidth * xFactor) - 40.dp, y = (boxHeight * yFactor) - 15.dp)) {
        Button(
            onClick = { viewModel.toggleBodyPart(name) }, // Use toggleBodyPart
            modifier = Modifier.size(80.dp, 30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (name in selectedParts) Color.Green else Color.Black.copy(alpha = 0.2f),
                contentColor = Color.White
            )
        ) {
            Text(name, fontSize = 10.sp)
        }
    }
}



