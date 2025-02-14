plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.bridge.androidtechnicaltest"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "com.bridge.androidtechnicaltest"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
        testInstrumentationRunner = "com.bridge.androidtechnicaltest.config.CustomTestRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}


dependencies {

    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.appCompat)
    implementation(libs.androidx.work.runtime.ktx)

    implementation(libs.okhttp)
    implementation(libs.okhttpInterceptor)

    implementation(libs.androidx.lifecycleCompiler)
    implementation(libs.androidx.lifecycleExtensions)
    implementation(libs.androidx.lifecycleKTX)

    implementation(libs.dagger.hiltAndroid)
    implementation(libs.androidx.junit.ktx)
    androidTestImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.androidx.test.rules)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.work.testing)
    debugImplementation(libs.androidx.ui.test.junit4.android)
    debugImplementation(libs.androidx.runner)
    ksp(libs.dagger.hiltAndroidCompiler)
    kspAndroidTest(libs.dagger.hiltAndroidCompiler)
    ksp(libs.dagger.hiltCompiler)
    androidTestImplementation(libs.dagger.android.testing)

    implementation(libs.coil.svg)
    implementation(libs.coil)
    implementation(libs.coil.compose)

    implementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))
    implementation(libs.compose.materialDesign3)
    implementation(libs.compose.preview)
    debugImplementation(libs.compose.tooling)
    implementation(libs.compose.viewModel)
    implementation(libs.compose.liveData)
    implementation(libs.compose.navigation)

    implementation(libs.lottie.compose)
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)

    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)


    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    implementation(libs.junit)
}
