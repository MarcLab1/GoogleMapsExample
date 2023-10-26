plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    kotlin("kapt")
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}


android {
    namespace = "com.googlemapsapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.googlemapsapi"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation(com.googlemapsapi.Libs.AndroidX.core)
    implementation(com.googlemapsapi.Libs.AndroidX.core)
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation(com.googlemapsapi.Libs.AndroidX.Compose.ui)
    implementation(com.googlemapsapi.Libs.AndroidX.Compose.material3)
    implementation(com.googlemapsapi.Libs.AndroidX.Compose.graphics)
    implementation(com.googlemapsapi.Libs.AndroidX.Compose.preview)
    implementation("com.google.android.libraries.places:places:3.2.0")
    implementation("androidx.media3:media3-session:1.1.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    val nav_version = "2.7.1"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.3.0")

    // implementation("com.squareup.retrofit2:convertermoshi:2.9.0")
    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    implementation("com.google.dagger:dagger:2.47")
    ksp("com.google.dagger:dagger-compiler:2.47")

    val hilt_ = "2.47"
    implementation("com.google.dagger:hilt-android:$hilt_")
    ksp("com.google.dagger:hilt-android-compiler:$hilt_")

    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

   // implementation("org.jetbrains.kotlin:kotlin-stdlib")

    //implementation ("io.github.raamcosta.compose-destinations:animations-core:1.9.54")
    //ksp ("io.github.raamcosta.compose-destinations:ksp:1.9.54")
    //implementation("com.google.accompanist:accompanist-navigation-animation:1.5.1")

    implementation( "com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")

    implementation ("com.google.android.gms:play-services-location:21.0.1")
    implementation ("com.google.maps.android:maps-compose:2.15.0")
    implementation ("com.google.android.gms:play-services-maps:18.1.0")

    //Accompanist (Permission)
    implementation ("com.google.accompanist:accompanist-permissions:0.31.3-beta")
    implementation("com.google.maps:google-maps-services:2.2.0")
    implementation("com.google.android.libraries.places:places:3.1.0")
    implementation("com.google.maps:google-maps-services:2.2.0")
}