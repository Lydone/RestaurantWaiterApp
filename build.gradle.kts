plugins {
    id(Plugin.Android.APPLICATION) version Version.AGP apply false
    id(Plugin.Android.LIBRARY) version Version.AGP apply false
    id(Plugin.DAGGER_HILT) version Version.Dagger.HILT apply false
    id(Plugin.KSP) version Version.KSP apply false
    id(Plugin.GOOGLE_SERVICES) version Version.GOOGLE_SERVICES apply false
    kotlin(Plugin.Kotlin.ANDROID) version Version.KOTLIN apply false
    kotlin(Plugin.Kotlin.SERIALIZATION) version Version.KOTLIN apply false
}
