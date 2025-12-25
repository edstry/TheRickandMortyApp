plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.edstry.therickandmortyapp"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.edstry.therickandmortyapp"
        minSdk = 26
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
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.firebase.crashlytics.buildtools)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)


    // hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.work)
    ksp (libs.hilt.compiler)
    ksp(libs.androidx.hilt.compiler)
    // navigation
    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    // room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    ksp(libs.androidx.room.compiler)
    // WorkManager
    implementation(libs.androidx.work.runtime.ktx)
    // viewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // serialization kotlin
    implementation(libs.kotlinx.serialization.json)
    // coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    // splash screen
    implementation(libs.androidx.core.splashscreen)
    // retrofit and converter and logging-interceptor
    implementation(libs.retrofit)
    implementation(libs.converter.kotlinx.serialization)
    implementation(libs.logging.interceptor)
    // datastore preference
    implementation(libs.androidx.datastore.preferences)
    // Paging 3
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
}