plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.ammad.splash_impl"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    //koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    //navigation
    implementation(libs.navigation3.ui)
    implementation(libs.navigation3.runtime)

    //modules
    implementation(project(":core:shared"))
    //implementation(project(":core:navigation"))
    implementation(project(":feature:splash:splash-api"))
}