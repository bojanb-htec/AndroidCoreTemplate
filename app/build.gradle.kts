plugins {


    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
    // only-for-crashlytics: alias(libs.plugins.com.google.firebase.crashlytics)
    // only-for-app-distribution: alias(libs.plugins.com.google.firebase.appdistribution)
    alias(libs.plugins.com.google.gms.google.services)

    /**
     * #DataBindingSample
     * There is no support for ksp with databinding (kapt should be used instead).
     *
     * Change with the following line to enable databinding example
     *
     * alias(libs.plugins.org.jetbrains.kotlin.kapt)
     */
    alias(libs.plugins.com.google.developers.ksp)
}

apply(from = Config.___APP_NAME_CAMEL___.detekt)

android {
    namespace = "${Config.___APP_NAME_CAMEL___.applicationId}.app"
    compileSdk = Config.___APP_NAME_CAMEL___.compileSdkVersion

    defaultConfig {
        applicationId = Config.___APP_NAME_CAMEL___.applicationId
        minSdk = Config.___APP_NAME_CAMEL___.minSdkVersion
        targetSdk = Config.___APP_NAME_CAMEL___.targetSdkVersion
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = Config.___APP_NAME_CAMEL___.instrumentationRunner
        multiDexEnabled = Config.___APP_NAME_CAMEL___.multiDex
    }

    signingConfigs {

        Signing.loadReleaseProperties(project)

        create(Config.___APP_NAME_CAMEL___.release) {
            if (Signing.keystoreProperties.isNotEmpty()) {
                keyAlias = Signing.keyAlias
                keyPassword = Signing.keyPassword
                storeFile = Signing.getStoreFile(project)
                storePassword = Signing.storePassword
            }
        }
    }

    buildTypes {
        getByName(Config.___APP_NAME_CAMEL___.release) {
            isMinifyEnabled = Config.___APP_NAME_CAMEL___.minifyEnabled
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName(Config.___APP_NAME_CAMEL___.release)
        }
        // only-for-app-distribution: getByName(Config.___APP_NAME_CAMEL___.debug) {
        // only-for-app-distribution:     firebaseAppDistribution {
        // only-for-app-distribution:         groups = Config.FirebaseDistribution.groups
        // only-for-app-distribution:         releaseNotesFile = Config.FirebaseDistribution.releaseNotesFile
        // only-for-app-distribution:         // serviceCredentialsFile = Config.FirebaseDistribution.serviceCredentialsFile
        // only-for-app-distribution:     }
        // only-for-app-distribution: }
    }
    compileOptions {
        sourceCompatibility = Config.___APP_NAME_CAMEL___.javaVersion
        targetCompatibility = Config.___APP_NAME_CAMEL___.javaVersion
    }

    kotlinOptions {
        jvmTarget = Config.___APP_NAME_CAMEL___.javaVersion.toString()
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(platform(libs.htecgroup.androidcore.bom))
    implementation(libs.htecgroup.androidcore.domain)
    implementation(libs.htecgroup.androidcore.data)
    implementation(libs.htecgroup.androidcore.presentation)

    // Hilt
    implementation(libs.dagger.hilt)
    implementation(libs.androidx.hilt.navigation.compose)
    /**
     * #DataBindingSample
     * Change with the following line to enable databinding example
     * kapt(libs.dagger.hilt.compiler)
     */
    ksp(libs.dagger.hilt.compiler)

    implementation(libs.play.services.ads)

    implementation(platform(libs.firebase.bom))
    // only-for-crashlytics: implementation(libs.firebase.crashlytics.ktx)
    // only-for-analytics: implementation(libs.firebase.analytics.ktx)
    // only-for-push-notifications: implementation(libs.firebase.messaging.ktx)

    implementation(libs.androidx.work.runtime.ktx)

}