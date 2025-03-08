/*package com.example.calleme.Database

import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.jan.supabase.postgrest.postgrest

fun getHospitalName() {
    val viewModelScope
    viewModelScope.launch {
        try {
            _userState.value = UserState.Loading
            val data = SupabaseClient.client.postgrest["hospitals"]
                .select().decodeSingle<name>()
            _userState.value = UserState.Success("Data: $data.name")
        } catch (e:Exception) {
            _userState.value = UserState.Error(e.message.toString())
        }
    }
}*/