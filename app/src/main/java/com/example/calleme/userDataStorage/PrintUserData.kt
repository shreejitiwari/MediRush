package com.example.calleme.UserDataStorage

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.calleme.ui.theme.GreenPrimary


@Composable
fun ResultScreen(navController: NavController, viewModel: EmergencyViewModel = viewModel()) {
    val selectedBodyParts by viewModel.selectedBodyParts.collectAsState()
    val problemDescription by viewModel.problemDescription.collectAsState()
    val date by viewModel.date.collectAsState()
    val time by viewModel.time.collectAsState()
    val location by viewModel.location.collectAsState()
    val audioPath by viewModel.audioFilePath.collectAsState()
    val attachedFiles by viewModel.attachedFiles.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Selected Body Parts:", fontWeight = FontWeight.Bold)
        if (selectedBodyParts.isNotEmpty()) {
            selectedBodyParts.forEach { bodyPart ->
                Text(text = "- $bodyPart")
            }
        } else {
            Text(text = "No body parts selected")
        }

        Text(text = "\nProblem Description:", fontWeight = FontWeight.Bold)
        Text(text = problemDescription.ifEmpty { "No problem description provided" })

        Text(text = "\nDate:", fontWeight = FontWeight.Bold)
        Text(text = date.ifEmpty { "No date selected" })

        Text(text = "\nTime:", fontWeight = FontWeight.Bold)
        Text(text = time.ifEmpty { "No time selected" })

        Text(text = "\nLocation:", fontWeight = FontWeight.Bold)
        Text(text = location.ifEmpty { "No location provided" })

        Text(text = "\nAudio File Path:", fontWeight = FontWeight.Bold)
        Text(text = audioPath.ifEmpty { "No audio recorded" })

        Text(text = "\nAttached Files:", fontWeight = FontWeight.Bold)
        if (attachedFiles.isNotEmpty()) {
            attachedFiles.forEach { file ->
                Text(text = "- ${file.substringAfterLast('/')} ")
            }
        } else {
            Text(text = "No files attached")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}

