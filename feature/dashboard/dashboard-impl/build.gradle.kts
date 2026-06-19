import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.ammad.dashboard_impl"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val secretsFile = rootProject.file("secrets.properties")
        val secretKey = if (secretsFile.exists()) {
            val properties = Properties()
            properties.load(secretsFile.inputStream())
            properties.getProperty("API_KEY")
           // "\"${properties.getProperty("API_KEY")}\""
        } else {
            "\"MISSING_KEY\""
        }

        buildConfigField("String", "API_KEY", secretKey)
    }
    buildFeatures {
        buildConfig = true
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

    //core:network
    implementation(project(":core:network"))

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.convert.gson)

    //koin
    implementation(libs.koin.android)

    //modules
    implementation(project(":core:shared"))
    implementation(project(":core:navigation"))
}