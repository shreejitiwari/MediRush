package com.example.calleme

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FormViewModel : ViewModel() {

    val dateState = MutableStateFlow("dd/mm/yyyy")
    val timeState = MutableStateFlow("hh:mm:ss")
    val locationState = MutableStateFlow("Fetching location...")
    private val _filesList = MutableStateFlow<List<Uri>>(emptyList())
    val filesList: StateFlow<List<Uri>> = _filesList

    // MutableStateFlow to store form data
    private val _problemText = MutableStateFlow("")
    val problemText: StateFlow<String> = _problemText

    private val _date = MutableStateFlow("dd/mm/yyyy")
    val date: StateFlow<String> = _date

    private val _time = MutableStateFlow("hh:mm:ss")
    val time: StateFlow<String> = _time

    private val _location = MutableStateFlow("Fetching location...")
    val location: StateFlow<String> = _location

    private val _fileList = MutableStateFlow<List<Uri>>(emptyList())
    val fileList: StateFlow<List<Uri>> = _fileList

    private val _affectedAreaFront = MutableStateFlow<Uri?>(null)
    val affectedAreaFront: StateFlow<Uri?> = _affectedAreaFront

    private val _affectedAreaBack = MutableStateFlow<Uri?>(null)
    val affectedAreaBack: StateFlow<Uri?> = _affectedAreaBack

    // Functions to update state
    /*fun updateProblemText(newText: String) {
        _problemText.value = newText
    }

    fun updateDate(newDate: String) {
        _date.value = newDate
    }

    fun updateTime(newTime: String) {
        _time.value = newTime
    }

    fun updateLocation(newLocation: String) {
        _location.value = newLocation
    }

    fun updateFiles(newFiles: List<Uri>) {
        _fileList.value = newFiles
    }

    fun updateAffectedAreaFront(newUri: Uri?) {
        _affectedAreaFront.value = newUri
    }

    fun updateAffectedAreaBack(newUri: Uri?) {
        _affectedAreaBack.value = newUri
    }*/

    fun setProblemText(text: String) {
        _problemText.value = text
    }

    fun setDate(date: String) {
        dateState.value = date
    }

    fun setTime(time: String) {
        timeState.value = time
    }

    fun setLocation(location: String) {
        locationState.value = location
    }

    fun addFiles(uris: List<Uri>) {
        _filesList.value = uris
    }

    fun removeFile(uri: Uri) {
        _filesList.value = _filesList.value - uri
    }
}
