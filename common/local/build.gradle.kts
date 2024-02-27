plugins {
    id(ModulePlugin.MODULE_NAME)
}

android {
    namespace = "com.raven.local"
}

dependencies {
    di()
    general()
    testing()
    network()
    room()
}
