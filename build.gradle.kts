buildscript {
    repositories {
        mavenCentral()
        google()
        maven { url = java.net.URI("https://jitpack.io") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.22")
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        maven { url = java.net.URI("https://jitpack.io") }
    }
}
