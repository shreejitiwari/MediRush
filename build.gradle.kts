// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    kotlin("plugin.serialization") version "2.0.0"
    kotlin("jvm") version "2.0.0" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}

//dependencies {
//    implementation(platform("io.github.jan-tennert.supabase:bom:0.1.0"))
//    implementation("io.github.jan-tennert.supabase:postgrest-kt")
//    implementation("io.ktor:ktor-client-android:2.0.0")
//}