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
    implementation(platform("io.github.jan-tennert.supabase:bom:3.1.2"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt:3.1.2")


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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}