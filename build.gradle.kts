buildscript {
    repositories {
        mavenCentral()
        google()
        maven { url = java.net.URI("https://raw.githubusercontent.com/tachiyomiorg/tachiyomi-maven/master/2.0/") }
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
        maven { url = java.net.URI("https://raw.githubusercontent.com/tachiyomiorg/tachiyomi-maven/master/2.0/") }
    }
}
