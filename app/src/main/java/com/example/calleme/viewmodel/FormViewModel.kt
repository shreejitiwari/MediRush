package com.example.calleme.viewmodel

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.ViewModel


class FormViewModel : ViewModel() {
    val problemText = mutableStateOf("")
    val dateState = mutableStateOf("dd/mm/yyyy")
    val timeState = mutableStateOf("hh:mm:ss")
    val locationState = mutableStateOf("Fetching location...")
    val filesList = mutableStateListOf<Uri>()
}