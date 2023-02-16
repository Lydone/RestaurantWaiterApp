object Dependency {

    const val CORE_KTX = "androidx.core:core-ktx:1.9.0"

    const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:2.5.3"

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