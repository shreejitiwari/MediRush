package com.example.calleme.Database

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = "https://cixfdcrvjlfppyyyuufe.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNpeGZkY3J2amxmcHB5eXl1dWZlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDEyOTQ2NjYsImV4cCI6MjA1Njg3MDY2Nn0.S5Ry8z3d7-e_kh4vLRq6k3UAFT3Oa30oSR8Ne86VeVw"
    ) {
        install(Postgrest)
    }
}