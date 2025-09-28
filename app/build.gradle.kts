plugins {
    // android
    id("com.android.application")
    // jetbrains
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
}

android {

    namespace  = "ir.farsroidx.andromeda"
    compileSdk = 34

    defaultConfig {
        applicationId             = "ir.farsroidx.andromeda"
        minSdk                    = 21
        targetSdk                 = 34
        versionCode               = 1
        versionName               = "0.0.1"
        multiDexEnabled           = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(project(":m31-views-additives")) // += lib-foundation

    // Module
    implementation(project(":m31-api"))
    implementation(project(":m31-cache-file"))
    implementation(project(":m31-compose"))
    implementation(project(":m31-compose-additives"))
    implementation(project(":m31-database"))
    implementation(project(":m31-exception-ui"))
    implementation(project(":m31-preferences"))
    implementation(project(":m31-socket-io"))
    implementation(project(":m31-views"))

    // Android-X
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.activity:activity-ktx:1.9.0")

    // Farsroidx
    implementation("com.github.farsroidx:andromeda-core:1.0.0")
    implementation("com.github.farsroidx:andromeda-viewmodel:1.0.0")
    implementation("com.github.farsroidx:andromeda-memory-cache:1.0.0")
}