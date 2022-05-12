plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.mrb.loteriadenavidad"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        resources.excludes.add("META-INF/licenses/**")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
}

dependencies {
    implementation(project(":shared"))

    // Android
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")

    // Compose
    implementation("androidx.compose.ui:ui:1.1.1")
    implementation("androidx.compose.material:material:1.1.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.1.1")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.navigation:navigation-compose:2.4.2")
    implementation("androidx.compose.material:material-icons-extended:1.1.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.1.1")

    // Accompanist
    implementation("com.google.accompanist:accompanist-swiperefresh:0.20.0")
    implementation("com.google.accompanist:accompanist-insets:0.20.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.20.0")
    implementation("com.google.accompanist:accompanist-pager:0.20.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.20.0")

    // Views
    implementation("com.airbnb.android:lottie:4.1.0")
    implementation("com.airbnb.android:lottie-compose:4.1.0")
    implementation("com.valentinilk.shimmer:compose-shimmer:1.0.1")

    // Koin
    implementation("io.insert-koin:koin-core:3.1.6")
    implementation("io.insert-koin:koin-android:3.1.6")
    implementation("io.insert-koin:koin-androidx-compose:3.1.6")
    implementation("io.insert-koin:koin-test:3.1.6")
    implementation("io.insert-koin:koin-test-junit4:3.1.6")

    // utils
    implementation("androidx.browser:browser:1.4.0")
}
