package com.googlemapsapi

object Versions {
    const val ktlint = "0.45.2"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.2.1"
    //...

    object Kotlin {
        private const val version = "1.7.0"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        //...
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.4.1"
        const val compose = "androidx.activity:activity-compose:1.7.2"
        const val core = "androidx.core:core-ktx:1.10.1"

        object Compose {
            const val ui = "androidx.compose.ui:ui"
            const val graphics = "androidx.compose.ui:ui-graphics"
            const val preview = "androidx.compose.ui:ui-tooling-preview"
            const val material3 = "androidx.compose.material3:material3"
        }
    }
}