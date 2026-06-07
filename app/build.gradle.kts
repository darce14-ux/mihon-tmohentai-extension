plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlinx-serialization")
}

android {
    compileSdk = 34
    namespace = "eu.kanade.tachiyomi.extension.es.tmohentai"

    defaultConfig {
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("org.jsoup:jsoup:1.16.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    
    // Librerías esenciales que necesita el código de TMOHentai para no dar errores
    implementation("io.reactivex:rxjava:1.3.8")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
}
