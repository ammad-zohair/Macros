import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.ammad.dashboard_impl"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val secretsFile = rootProject.file("secrets.properties")
        val secretKey = if (secretsFile.exists()) {
            val properties = Properties()
            properties.load(secretsFile.inputStream())
            properties.getProperty("API_KEY")
        } else {
            "\"MISSING_KEY\""
        }

        buildConfigField("String", "API_KEY", secretKey)
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    implementation(libs.material.icons.core)
    debugImplementation(libs.androidx.ui.tooling)

    //compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.runtime.saveable)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.convert.gson)

    //koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    //navigation
    implementation(libs.navigation3.ui)
    implementation(libs.navigation3.runtime)

    //modules
    implementation(project(":core:shared"))
    implementation(project(":core:navigation"))
    implementation(project(":core:network"))
    implementation(project(":core:design-system"))
    implementation(project(":feature:dashboard:dashboard-api"))
    implementation(project(":feature:favorite:favorite-api"))
    implementation(project(":feature:log:log-api"))
}