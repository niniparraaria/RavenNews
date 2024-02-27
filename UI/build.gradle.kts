plugins {
    id(ModulePlugin.MODULE_NAME)
}

android {
    namespace = "com.raven.ui"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
}

dependencies {
    di()
    general()
    compose()
    coil()
}
