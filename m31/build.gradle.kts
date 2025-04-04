import org.gradle.language.nativeplatform.internal.Dimensions.applicationVariants

plugins {
    // android
    id("com.android.library")
    // jetbrains
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    // maven
    id("maven-publish")
}

android {

    namespace  = "ir.farsroidx.m31"
    compileSdk = 35

    defaultConfig {
        multiDexEnabled           = true
        minSdk                    = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {

        release {

            isMinifyEnabled = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "r8-rules.pro"
            )
        }
    }

    buildFeatures {
        buildConfig = false
        dataBinding = true
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

    // Android-X
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.multidex:multidex:2.0.1")

    // Base Module noinspection GradleDynamicVersion
    api("com.github.farsroidx:andromeda-foundation:2.0.1")

    // Koin
    implementation("io.insert-koin:koin-android:4.0.3")

    // Andromeda
    implementation("com.github.farsroidx:andromeda-extensions:2.1.0")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.7")
}

publishing {

    publications {

        register<MavenPublication>("release") {

            groupId    = "ir.farsroidx.m31"
            artifactId = "andromeda-core"
            version    = "2.1.0"

            afterEvaluate {

                from(
                    components["release"]
                )
            }
        }
    }
}