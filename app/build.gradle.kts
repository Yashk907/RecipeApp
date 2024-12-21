plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.recipeapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.recipeapp"
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
    //google fonts
    implementation("androidx.compose.ui:ui-text-google-fonts:1.7.5")
    //google icons
    implementation("androidx.compose.material:material-icons-extended:1.7.5")

//Room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1") // For annotation processing
    implementation("androidx.room:room-ktx:2.6.1") // For Kotlin extensions and coroutines support
    testImplementation("androidx.room:room-testing:2.6.1")


//HILT
    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // ViewModel for Jetpack Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    // Optional: LiveData support
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    // Optional: SavedState module for ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.1")

    //navigation
    implementation("androidx.navigation:navigation-compose:2.7.4")
    // Hilt Navigation for Jetpack Compose
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")

    //Gson
    implementation("com.google.code.gson:gson:2.10.1")

    implementation("androidx.work:work-runtime-ktx:2.10.0")//workManager

    //coil
    implementation("io.coil-kt.coil3:coil-compose:3.0.4")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.4")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}