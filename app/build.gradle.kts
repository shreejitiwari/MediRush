plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.calleme"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.calleme"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.10.0"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.android.gms:play-services-auth:20.5.0")

    //Supbase DATABASE
    implementation("io.ktor:ktor-client-android:2.3.3") // HTTP client for API calls
    // Import the BOM for supabase-kt to manage versions
    implementation(platform("io.github.jan-tennert.supabase:bom:3.1.1"))
    // Add the specific modules you need
    implementation("io.github.jan-tennert.supabase:auth-kt:3.1.1")
    implementation("io.github.jan-tennert.supabase:postgrest-kt:3.1.1")
    implementation("io.github.jan-tennert.supabase:realtime-kt:3.1.1")
    implementation("io.github.jan-tennert.supabase:storage-kt:3.1.1")
    //implementation("io.github.jan-tennert.supabase:gotrue-kt")
    implementation("io.ktor:ktor-client-core:3.1.1")
    implementation("io.ktor:ktor-client-cio:3.1.1") // CIO engine for networking
    implementation("io.ktor:ktor-client-android:3.1.1") // Android HTTP engine
    implementation("io.ktor:ktor-client-logging:3.1.1") // Optional: Logging for debugging
    implementation("io.ktor:ktor-client-content-negotiation:3.1.1") // JSON handling
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.1.1") // Serialization
    implementation("io.coil-kt:coil-compose:2.5.0") // For Images
    //implementation("io.supabase:supabase-android:1.0.0")
    implementation("io.ktor:ktor-client-android:3.1.1")
    implementation("io.ktor:ktor-client-okhttp:3.1.1")
    implementation("io.ktor:ktor-client-auth:3.1.1")



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.ui)
    implementation("androidx.navigation:navigation-ui:2.8.8")
    implementation("androidx.navigation:navigation-compose:2.8.8")
    implementation("androidx.compose.ui:ui:1.7.8")
    implementation("androidx.compose.material3:material3:1.3.1")
    // Accompanist Permissions (for runtime permission handling)
    implementation("com.google.accompanist:accompanist-permissions:0.31.3-beta")
    // Google Play Services Location (for GPS)
    implementation ("com.google.android.gms:play-services-location:21.0.1")
    implementation("androidx.compose.material:material-icons-extended:1.5.0")
    implementation("androidx.compose.foundation:foundation:1.7.8")
    implementation(libs.androidx.media3.common.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}