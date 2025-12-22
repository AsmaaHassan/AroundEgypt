plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android) // Added Hilt plugin
    alias(libs.plugins.ksp)         // Added KSP for annotation processing
//    alias(libs.plugins.kotlin.compose.compiler)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.aroundegypt"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.aroundegypt"
        minSdk = 34
        targetSdk = 36
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
    // Core & Lifecycle
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    // Compose BOM and Dependencies
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.graphics)
    implementation(libs.androidx.compose.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.icons.extended)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Hilt for Dependency Injection
    implementation(libs.hilt.android)
    implementation(libs.androidx.browser)
    ksp(libs.hilt.compiler) // Use ksp() instead of kapt()

    // Network (Retrofit, OkHttp, Moshi)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.codegen) // Use ksp() for Moshi's code generation
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging)

    // Coroutines
    implementation(libs.coroutines.android)

    // Security
    implementation(libs.androidx.security.crypto)

    // Testing - JUnit 4 (for compatibility)
    testImplementation(libs.junit)

    // Testing - JUnit 5
    testImplementation(libs.junit5.api)
    testRuntimeOnly(libs.junit5.engine)

    // Testing - MockK (for mocking in Kotlin)
    testImplementation(libs.mockk)

    // Testing - Turbine (for Flow testing)
    testImplementation(libs.turbine)

    // Testing - Kotest (for property-based and expressive testing)
    testImplementation(libs.kotest.runner)
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.kotest.property)

    // Testing - Coroutines Test
    testImplementation(libs.coroutines.test)

    // Testing - ViewModel Test (for viewModelScope testing)
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    // AndroidTest dependencies
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.manifest)

    // Debug
    debugImplementation(libs.androidx.compose.tooling.preview)

    val room_version = "2.8.4"

    implementation("androidx.room:room-runtime:$room_version")

    // If this project uses any Kotlin source, use Kotlin Symbol Processing (KSP)
    // See Add the KSP plugin to your project
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:${room_version}")
    implementation("androidx.room:room-paging:${room_version}")

    
    //coil
    implementation("io.coil-kt:coil-compose:2.5.0")


    implementation("androidx.compose.material3:material3-adaptive-navigation-suite:1.4.0")
    implementation("androidx.activity:activity-ktx:1.0.0")
}