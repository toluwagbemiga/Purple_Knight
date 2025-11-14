plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // CORRECTED: Use aliases to respect the versions in your libs.versions.toml
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp) // Use KSP instead of the older kapt
}

android {
    namespace = "com.bit.purple.project"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.bit.purple.project"
        minSdk = 24
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
    // Your composeOptions can be removed as the BOM handles this, but keeping it is fine.
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    // --- Your Existing Dependencies (Unchanged) ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)

    // Removed duplicate and conflicting dependencies
    // implementation(libs.activity.compose) -> Duplicate
    // implementation(platform(libs.compose.bom)) -> Duplicate
    // implementation(libs.bundles.compose) -> Redundant with individual compose dependencies
    // implementation(libs.navigation.compose) -> Duplicate

    implementation(libs.androidx.hilt.navigation.compose)

    // --- Dependencies using ksp ---
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler) // CORRECTED: Use ksp instead of kapt

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler) // CORRECTED: Use ksp instead of kapt

    // Gson
    //implementation(libs.gson)

    implementation(libs.gson)

    // DataStore
    implementation(libs.androidx.datastore.preferences)


    //custom implementation
    implementation(libs.androidx.compose.material.icons.extended)

    // --- Your Testing Dependencies (Unchanged) ---
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

