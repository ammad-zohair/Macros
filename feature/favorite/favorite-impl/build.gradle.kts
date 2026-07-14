plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.favorite_impl"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        minSdk = 26

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

    //compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.material.icons.core)

    //koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    //navigation
    implementation(libs.navigation3.ui)
    implementation(libs.navigation3.runtime)

    //modules
    implementation(project(":core:navigation"))
    implementation(project(":core:shared"))
    implementation(project(":core:database"))
    implementation(project(":core:design-system"))
    implementation(project(":feature:favorite:favorite-api"))
    implementation(project(":feature:dashboard:dashboard-api"))
    implementation(project(":feature:log:log-api"))
}