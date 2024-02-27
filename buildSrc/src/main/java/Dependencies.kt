import Dependencies.appCompat
import Dependencies.coil
import Dependencies.composeBom
import Dependencies.composeMaterial
import Dependencies.composePreview
import Dependencies.composeTooling
import Dependencies.composeUI
import Dependencies.composeVM
import Dependencies.coreTesting
import Dependencies.coroutines
import Dependencies.coroutinesAndroid
import Dependencies.coroutinesTest
import Dependencies.espressoCore
import Dependencies.extJunit
import Dependencies.gson
import Dependencies.hilt
import Dependencies.hiltCompiler
import Dependencies.junit
import Dependencies.kotlinCore
import Dependencies.ktxFragment
import Dependencies.lifeCycle
import Dependencies.lifecycleVM
import Dependencies.logginInterceptor
import Dependencies.material
import Dependencies.mockitoCore
import Dependencies.mockitoInline
import Dependencies.navigationFragment
import Dependencies.navigationUI
import Dependencies.okHttp
import Dependencies.retrofit
import Dependencies.retrofitConverter
import Dependencies.room
import Dependencies.roomCompiler
import Dependencies.roomCoroutines
import Dependencies.roomTest
import Dependencies.testRules
import Dependencies.testRunner
import Dependencies.truth
import Dependencies.turbine
import ext.androidTestImplementation
import ext.debugImplementation
import ext.implementation
import ext.kapt
import ext.testImplementation
import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    /** General **/
    const val kotlinCore = "androidx.core:core-ktx:${Versions.kotlinCore}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val lifeCycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val ktxFragment = "androidx.fragment:fragment-ktx:${Versions.ktxFragment}"
    const val lifecycleVM = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"


    /** Testing **/
    const val junit = "junit:junit:${Versions.junit}"
    const val extJunit = "androidx.test.ext:junit-ktx:${Versions.extJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCore}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val testRules = "androidx.test:rules:${Versions.androidxTest}"
    const val testRunner = "androidx.test:runner:${Versions.androidxTest}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"
    const val turbine = "app.cash.turbine:turbine:${Versions.turbine}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    const val roomTest = "androidx.room:room-testing:${Versions.roomVersion}"
    /** Network **/

    val gson by lazy { "com.google.code.gson:gson:${Versions.gsonVersion}" }
    val okHttp by lazy { "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}" }
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}" }
    val retrofitConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}" }
    val logginInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}" }

    /** DI **/

    val hilt by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }

    /** Navigation **/

    val navigationUI by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}" }
    val navigationFragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}" }

    /** Compose **/

    val composeBom by lazy {"androidx.compose:compose-bom:${Versions.composeBomVersion}"}
    val composeUI by lazy { "androidx.compose.ui:ui" }
    val composeMaterial by lazy { "androidx.compose.material3:material3" }
    val composePreview by lazy { "androidx.compose.ui:ui-tooling-preview" }
    val composeTooling by lazy { "androidx.compose.ui:ui-tooling" }
    val composeVM by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeVMVersion}" }

    /** Room **/

    val room by lazy { "androidx.room:room-runtime:${Versions.roomVersion}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.roomVersion}" }
    val roomCoroutines by lazy { "androidx.room:room-ktx:${Versions.roomVersion}" }

    /** Coil **/

    val coil by lazy { "io.coil-kt:coil-compose:${Versions.coilVersion}" }

}

/** Dependencies **/

fun DependencyHandler.general() {
    implementation(material)
    implementation(appCompat)
    implementation(lifeCycle)
    implementation(coroutines)
    implementation(coroutinesAndroid)
    implementation(kotlinCore)
    implementation(ktxFragment)
    implementation(lifecycleVM)
}

fun DependencyHandler.testing() {
    testImplementation(junit)
    androidTestImplementation(extJunit)
    androidTestImplementation(espressoCore)
    androidTestImplementation(testRules)
    androidTestImplementation(testRunner)
    testImplementation(mockitoCore)
    testImplementation(mockitoInline)
    testImplementation(turbine)
    testImplementation(coreTesting)
    testImplementation(coroutinesTest)
    testImplementation(truth)
    testImplementation(roomTest)
}

fun DependencyHandler.network() {
    implementation(gson)
    implementation(okHttp)
    implementation(retrofit)
    implementation(logginInterceptor)
    implementation(retrofitConverter)
}

fun DependencyHandler.di() {
    kapt(hiltCompiler)
    implementation(hilt)
}

fun DependencyHandler.navigation() {
    implementation(navigationUI)
    implementation(navigationFragment)
}

fun DependencyHandler.compose() {
    implementation(platform(composeBom))
    implementation(composeUI)
    implementation(composeVM)
    implementation(composePreview)
    debugImplementation(composeTooling)
    implementation(composeMaterial)
}

fun DependencyHandler.room() {
    implementation(room)
    kapt(roomCompiler)
    implementation(roomCoroutines)
}

fun DependencyHandler.coil() {
    implementation(coil)
}
