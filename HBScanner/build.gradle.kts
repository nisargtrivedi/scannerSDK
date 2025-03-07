import org.jetbrains.kotlin.gradle.utils.IMPLEMENTATION

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {

    namespace = "com.hbscanner"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        consumerProguardFiles("consumer-rules.pro")
        multiDexEnabled = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug{

        }
    }
    compileOptions{
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility  = JavaVersion.VERSION_17
    }
}
dependencies {
    api ("com.regula.documentreader:api:7.5.10412")
}
