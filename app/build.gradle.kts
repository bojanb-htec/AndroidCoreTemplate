plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.android)
    kotlin(Plugins.kapt)
    id(Plugins.hilt)
    // only-for-crashlytics: id(Plugins.firebaseCrashlytics)
    // only-for-app-distribution: id(Plugins.firebaseAppDistribution)
}

apply(from = Config.___APP_NAME_CAMEL___.detekt)

android {
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
    }
}

dependencies {
    implementation(project(Config.Module.domain))
    implementation(project(Config.Module.data))
    implementation(project(Config.Module.presentation))

    // Hilt
    implementation(Libs.hilt_android)
    kapt(Libs.hilt_android_compiler)
    kapt(Libs.hilt_compiler)

    implementation(Libs.play_services_ads)

    implementation(platform(Libs.firebase_bom))
    // only-for-crashlytics: implementation(Libs.firebase_crashlytics_ktx)
    // only-for-analytics: implementation(Libs.firebase_analytics_ktx)
    // only-for-push-notifications: implementation(Libs.firebase_messaging_ktx)

    implementation(Libs.work_runtime_ktx)
}

apply(plugin = Plugins.googleServices)

repositories {
    mavenCentral()
}