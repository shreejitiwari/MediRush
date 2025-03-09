package com.example.calleme.EmergencyButton

import android.Manifest
import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.widget.TimePicker
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.calleme.R
import com.google.android.gms.location.*
import java.util.*
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calleme.ui.theme.GreenPrimary
import com.example.calleme.viewmodel.FormViewModel

@Composable
fun formScreen(navController: NavHostController, formViewModel: FormViewModel = viewModel()) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // Access ViewModel state
    var problemText by formViewModel.problemText
    var dateState by formViewModel.dateState
    var timeState by formViewModel.timeState
    var locationState by formViewModel.locationState
    val filesList = formViewModel.filesList

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year: Int, month: Int, day: Int ->
            formViewModel.dateState.value = "%02d/%02d/%d".format(day, month + 1, year)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val fileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        formViewModel.filesList.clear()
        formViewModel.filesList.addAll(uris)
    }

    val micPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Start recording audio (Implementation needed)
        }
    }

    val timePickerDialog = TimePickerDialog(
        context,
        { _: TimePicker, hour: Int, minute: Int ->
            formViewModel.timeState.value = String.format("%02d:%02d:00", hour, minute)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    )

    val fusedLocationProviderClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            getCurrentLocation(context, fusedLocationProviderClient) { location ->
                formViewModel.locationState.value = location
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
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
                text = "Fill The Form",
                fontSize = 21.sp,
                modifier = Modifier.padding(18.dp)
            )
        }

        // Description Field
        Text(text = "Describe your Problem")
        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = problemText,
            onValueChange = { formViewModel.problemText.value = it },
            placeholder = { Text("Enter your problem...") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            singleLine = false,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { micPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO) }, colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)) {
            Icon(Icons.Default.Mic, contentDescription = "Add Audio Description")
            Text(" Record Audio")
        }

        OutlinedTextField(
            value = dateState,
            onValueChange = { },
            label = { Text("Choose Date") },
            trailingIcon = {
                IconButton(onClick = { datePickerDialog.show() }) {
                    Icon(Icons.Default.CalendarToday, contentDescription = "Choose Date")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = timeState,
            onValueChange = { },
            label = { Text("Choose Time") },
            trailingIcon = {
                IconButton(onClick = { timePickerDialog.show() }) {
                    Icon(Icons.Default.AccessTime, contentDescription = "Choose Time")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = locationState,
            onValueChange = { },
            label = { Text("Choose Location") },
            trailingIcon = {
                IconButton(onClick = { locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION) }) {
                    Icon(Icons.Default.Place, contentDescription = "Choose Location")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { fileLauncher.launch("*/*") }, colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)) {
            Icon(Icons.Default.Folder, contentDescription = "Pick Files")
            Text(" Select Files")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .border(1.dp, Color.Gray)
            .padding(5.dp)
            .verticalScroll(rememberScrollState())
        ) {
            Column {
                filesList.forEachIndexed { index, uri ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = uri.toString().substringAfterLast("/"),
                                maxLines = 1
                            )
                            IconButton(onClick = { filesList.removeAt(index) }) {
                                Icon(Icons.Default.Close, contentDescription = "Remove File", tint = Color.Red)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { /* Upload Logic */ }, colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)) {
            Icon(Icons.Default.CloudUpload, contentDescription = "Upload Files")
            Text(" Upload Files")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate("findHospitals")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)
        ) {
            Text("Search")
        }
    }
}

@SuppressLint("MissingPermission")
fun getCurrentLocation(
    context: Context,
    fusedLocationClient: FusedLocationProviderClient,
    onLocationFetched: (String) -> Unit
) {
    if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED
    ) {
        return
    }

    fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
        location?.let {
            val latLng = "Lat: ${it.latitude}, Lng: ${it.longitude}"
            onLocationFetched(latLng)
        } ?: onLocationFetched("Location not found")
    }
}