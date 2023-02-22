object Dependency {

    const val COIL = "io.coil-kt:coil-compose:2.2.2"
    const val CORE_KTX = "androidx.core:core-ktx:1.9.0"

    const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:2.5.3"

    const val SERIALIZATION = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1"

    const val DATASTORE_PREFERENCES = "androidx.datastore:datastore-preferences:1.0.0"


    object Compose {

        const val UI = "androidx.compose.ui:ui:${Version.Compose.UI}"
        const val MATERIAL3 = "androidx.compose.material3:material3:${Version.Compose.MATERIAL3}"
        const val ACTIVITY = "androidx.activity:activity-compose:1.6.1"
        const val PREVIEW = "androidx.compose.ui:ui-tooling-preview:${Version.Compose.UI}"
    }

    object Dagger {

        const val HILT_ANDROID = "com.google.dagger:hilt-android:${Version.Dagger.HILT}"
        const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Version.Dagger.HILT}"
        const val HILT_NAVIGATION_COMPOSE = "androidx.hilt:hilt-navigation-compose:${Version.Dagger.HILT_NAVIGATION_COMPOSE}"
    }

    object Retrofit {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:2.9.0"
        const val CONVERTER = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"

    }

    object Room {
        const val RUNTIME = "androidx.room:room-runtime:${Version.ROOM}"
        const val COMPILER = "androidx.room:room-compiler:${Version.ROOM}"
        const val KTX = "androidx.room:room-ktx:${Version.ROOM}"
    }

    object Firebase {
        const val BOM = "com.google.firebase:firebase-bom:${Version.FIREBASE_BOM}"
        const val CLOUD_MESSAGING = "com.google.firebase:firebase-messaging-ktx"
    }
}