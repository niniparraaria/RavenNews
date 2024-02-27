import ext.implementation

plugins {
    id(ModulePlugin.MODULE_NAME)
}

android {
    namespace = "com.raven.home"

    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
}

dependencies {
    di()
    general()
    testing()
    network()
    compose()
    coil()
    room()
    ui()
    navigation()

    implementation(project(":core"))
    implementation(project(":common:network"))
    implementation(project(":common:local"))
    implementation(project(":UI"))
}
