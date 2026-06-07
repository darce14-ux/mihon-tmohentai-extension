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
}

dependencies {
    implementation("org.jsoup:jsoup:1.16.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    compileOnly("com.github.tachiyomiorg:source-api:5.0.0")
}
