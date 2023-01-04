plugins {
	id(Plugins.androidApplication)
	kotlin(Plugins.android)
	kotlin(Plugins.kapt)
	id(Plugins.hilt)
	id(Plugins.firebaseCrashlytics)
	id(Plugins.firebaseAppDistribution)
}

apply(from = "./" + Config.Core.detekt)
apply(from = "./" + Config.Core.moduleGraph)

android {
	namespace = "___PACKAGE_NAME___.app"
	compileSdk = Config.Core.compileSdkVersion

	defaultConfig {
		applicationId = Config.Core.applicationId
		minSdk = Config.SharedPref.minSdkVersion
		targetSdk = Config.SharedPref.targetSdkVersion
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = Config.SharedPref.instrumentationRunner
		multiDexEnabled = Config.Core.multiDex
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
	implementation(project(Config.Module.___APP_NAME_CAMEL___.domain))
	implementation(project(Config.Module.___APP_NAME_CAMEL___.data))
	implementation(project(Config.Module.___APP_NAME_CAMEL___.presentation))

	// Hilt
	implementation(Libs.hilt_android)
	kapt(Libs.hilt_android_compiler)
	kapt(Libs.hilt_compiler)

	implementation(Libs.play_services_ads)

	// Firebase TODO change to BOM
	implementation(Libs.firebase_core)
	implementation(Libs.firebase_crashlytics)
	implementation(Libs.firebase_messaging_ktx)

	implementation(Libs.work_runtime_ktx)
}

apply(plugin = Plugins.googleServices)

repositories {
	mavenCentral()
}