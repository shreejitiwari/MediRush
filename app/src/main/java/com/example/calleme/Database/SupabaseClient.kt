package com.example.calleme.Database

import android.content.Context
import android.net.Uri
import android.util.Log
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import io.github.jan.supabase.storage.UploadData // Import this for UploadData

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = "https://cixfdcrvjlfppyyyuufe.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNpeGZkY3J2amxmcHB5eXl1dWZlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDEyOTQ2NjYsImV4cCI6MjA1Njg3MDY2Nn0.S5Ry8z3d7-e_kh4vLRq6k3UAFT3Oa30oSR8Ne86VeVw"
    ) {
        install(Postgrest)
    }


    suspend fun uploadFile(context: Context, uri: Uri, bucket: String): String? {
        return withContext(Dispatchers.IO) {
            try {
                val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
                inputStream?.use { stream ->
                    val fileName = "uploads/${System.currentTimeMillis()}_${uri.lastPathSegment}"

                    // Convert InputStream to ByteArray
                    val byteArray = stream.readBytes()

                    // Upload the ByteArray to Supabase
                    client.storage.from(bucket).upload(fileName, byteArray)

                    return@withContext "https://cixfdcrvjlfppyyyuufe.supabase.co/storage/v1/object/public/$bucket/$fileName"
                }
            } catch (e: Exception) {
                Log.e("SupabaseUpload", "File upload failed: ${e.message}")
                null
            }
        }
    }

    suspend fun saveToDatabase(problemText: String, date: String, time: String, location: String, uploadedUrls: List<String>) {
        return withContext(Dispatchers.IO) {
            try {
                client.postgrest["your_table_name"].insert(
                    mapOf(
                        "problem_text" to problemText,
                        "date" to date,
                        "time" to time,
                        "location" to location,
                        "file_urls" to uploadedUrls.joinToString(",")
                    )
                )
                Log.d("SupabaseDB", "Data saved successfully!")
            } catch (e: Exception) {
                Log.e("SupabaseDB", "Error saving data: ${e.message}")
            }
        }
    }



}