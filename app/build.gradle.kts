plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.android)
    kotlin(Plugins.kapt)
    id(Plugins.hilt)
    id(Plugins.firebaseCrashlytics)
    id(Plugins.firebaseAppDistribution)
}

apply(from = Config.___APP_NAME___.detekt)

android {
    compileSdk = Config.___APP_NAME___.compileSdkVersion

    defaultConfig {
        applicationId = Config.___APP_NAME___.applicationId
        minSdk = Config.___APP_NAME___.minSdkVersion
        targetSdk = Config.___APP_NAME___.targetSdkVersion
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = Config.___APP_NAME___.instrumentationRunner
        multiDexEnabled = Config.___APP_NAME___.multiDex
    }

    signingConfigs {

        Signing.loadReleaseProperties(project)

        create(Config.___APP_NAME___.release) {
            if (Signing.keystoreProperties.isNotEmpty()) {
                keyAlias = Signing.keyAlias
                keyPassword = Signing.keyPassword
                storeFile = Signing.getStoreFile(project)
                storePassword = Signing.storePassword
            }
        }
    }

    buildTypes {
        getByName(Config.___APP_NAME___.release) {
            isMinifyEnabled = Config.___APP_NAME___.minifyEnabled
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName(Config.___APP_NAME___.release)
        }
        getByName(Config.___APP_NAME___.debug) {
            firebaseAppDistribution {
                groups = Config.FirebaseDistribution.groups
                releaseNotesFile = Config.FirebaseDistribution.releaseNotesFile
                serviceCredentialsFile = Config.FirebaseDistribution.serviceCredentialsFile
            }
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
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(project(Config.Module.domain))
    implementation(project(Config.Module.data))
    /**
     * #DataBindingSample
     * Change with the following line to enable databinding example
     * implementation(project(Config.Module.presentationDatabinding))
     */
    implementation(project(Config.Module.presentation))

    // Hilt
    implementation(Libs.hilt_android)
    kapt(Libs.hilt_android_compiler)
    kapt(Libs.hilt_compiler)

    implementation(Libs.play_services_ads)

    implementation(platform(Libs.firebase_bom))
    implementation(Libs.firebase_crashlytics_ktx)
    implementation(Libs.firebase_analytics_ktx)
    implementation(Libs.firebase_messaging_ktx)

    implementation(Libs.work_runtime_ktx)
}

apply(plugin = Plugins.googleServices)

repositories {
    mavenCentral()
}