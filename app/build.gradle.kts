plugins {
    id(Plugin.Android.APPLICATION)
    id(Plugin.DAGGER_HILT)
    kotlin(Plugin.Kotlin.ANDROID)
    kotlin(Plugin.Kotlin.KAPT)
    kotlin(Plugin.Kotlin.SERIALIZATION)
    id(Plugin.KSP)
    id(Plugin.GOOGLE_SERVICES)
}

android {
    namespace = "com.lydone.restaurantwaiterapp"
    compileSdk = Version.COMPILE_SDK

    defaultConfig {
        applicationId = "com.lydone.restaurantwaiterapp"
        minSdk = Version.MIN_SDK
        targetSdk = Version.TARGET_SDK
        versionCode = Version.VERSION_CODE
        versionName = Version.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        vectorDrawables {
//            useSupportLibrary true
//        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.Compose.COMPILER
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Dependency.COIL)
    implementation(Dependency.CORE_KTX)
//    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.3.1'
    implementation(Dependency.Compose.ACTIVITY)
    implementation(Dependency.Compose.UI)
    implementation(Dependency.Compose.PREVIEW)
    implementation(Dependency.Compose.MATERIAL3)
    implementation(Dependency.NAVIGATION_COMPOSE)
    implementation(Dependency.Dagger.HILT_ANDROID)
    implementation(Dependency.Dagger.HILT_NAVIGATION_COMPOSE)
    implementation(Dependency.Room.RUNTIME)
    implementation(Dependency.Room.KTX)
    annotationProcessor(Dependency.Room.COMPILER)
    ksp(Dependency.Room.COMPILER)
    kapt(Dependency.Dagger.HILT_ANDROID_COMPILER)
    implementation(platform(Dependency.Firebase.BOM))
    implementation(Dependency.Firebase.CLOUD_MESSAGING)
    implementation(Dependency.Retrofit.RETROFIT)
    implementation(Dependency.Retrofit.CONVERTER)
    implementation(Dependency.SERIALIZATION)
    // TODO remove asap
    implementation("androidx.compose.material:material:1.3.1")
    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
//    androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_version"
    debugImplementation("androidx.compose.ui:ui-tooling:${Version.Compose.UI}")
//    debugImplementation "androidx.compose.ui:ui-test-manifest:$compose_version"
}

kapt {
    correctErrorTypes = true
}