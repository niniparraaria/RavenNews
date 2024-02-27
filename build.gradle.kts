buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.hiltPlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
    }
}
