plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.project.travguide"
    compileSdk = 35

    buildFeatures {
        viewBinding = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true
    }

    defaultConfig {
        applicationId = "com.project.travguide"
        minSdk = 23
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.splashscreen)
    implementation(libs.glide)
    ksp(libs.glide.ksp)

<<<<<<< HEAD
    // Retrofit + Gson
    implementation(libs.retrofit)
    implementation(libs.gson.converter)
    implementation(libs.gson)

// Firebase (BOM & Auth)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)

// Lifecycle ViewModel + LiveData
    implementation(libs.viewmodel)
    implementation(libs.livedata)
    implementation(libs.lifecycle.runtime)

// Kotlin Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

=======
    implementation(platform(libs.firebase.bom))  // Firebase BOM
    implementation(libs.firebase.auth) // Firebase Auth
    implementation(libs.firebase.firestore) // Firebase Firestore
    implementation(libs.firebase.analytics) // Firebase Analytics
>>>>>>> 152816158d6e9ab8254eb5398993969fbc7d9a38

}