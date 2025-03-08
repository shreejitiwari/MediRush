package com.example.calleme.Database

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.filter.FilterOperation
import io.github.jan.supabase.postgrest.query.filter.FilterOperator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.coroutines.withContext


object SupabaseService {
    private val client = createSupabaseClient(
        supabaseUrl = "https://cixfdcrvjlfppyyyuufe.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzIiwi..." // Keep secret
    ) {
        install(Postgrest)
    }

    @Serializable
    data class Hospital(
        val id: Int,
        val name: String,
        val address: String,
        val phone: String
    )

    // ✅ Fetch hospitals (with optional search)
    suspend fun getHospitals(query: String? = null): List<Hospital> {
        return withContext(Dispatchers.IO) {
            try {
                val request = client.from("hospitals").select()
                val result = request.decodeList<Hospital>()
                if (query.isNullOrBlank()) result
                else result.filter { it.name.contains(query, ignoreCase = true) }
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }

    // ✅ Fetch hospital by ID
    suspend fun getHospitalById(hospitalId: String): Hospital? {
        return withContext(Dispatchers.IO) {
            try {
                client.from("hospitals")
                    .select()
//                    .eq("id", hospitalId.toInt())   // IS ERROR KO FIX KAR DOO TABHI PATA CHALAI GAA KI DARTABASE CONNECRTED HUHAA KI NAHI
                    .decodeSingleOrNull<Hospital>()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}
