plugins {
	id(Plugins.androidLibrary)
	kotlin(Plugins.android)
	kotlin(Plugins.kapt)
	id(Plugins.junit5)
	id(Plugins.hilt)
}

android {
	namespace = "___PACKAGE_NAME___.data"
	compileSdk = Config.Core.compileSdkVersion

	defaultConfig {
		minSdk = Config.Core.minSdkVersion
		targetSdk = Config.Core.targetSdkVersion

		testInstrumentationRunner = Config.SharedPref.instrumentationRunner
		consumerProguardFiles("consumer-rules.pro")

		javaCompileOptions {
			annotationProcessorOptions {
				mutableMapOf("room.schemaLocation" to "$projectDir/schemas")

			}
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
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}

	kotlinOptions {
		jvmTarget = JavaVersion.VERSION_11.toString()
	}

	lint {
		isAbortOnError = false
	}

	testOptions {
		unitTests.apply {
			isReturnDefaultValues = true
			isIncludeAndroidResources = true
		}
	}
}

dependencies {
	implementation("com.htec.bojanb.core:data:1.0.0")
	implementation(project(Config.Module.___APP_NAME_CAMEL___.domain))


	implementation(Square.retrofit2.retrofit)
	implementation(Square.retrofit2.converter.moshi)
	implementation(Square.moshi.kotlinReflect)

	// Hilt
	implementation(Libs.hilt_android)
	kapt(Libs.hilt_android_compiler)
	kapt(Libs.hilt_compiler)

	// Room
	implementation(Libs.room_runtime)
	kapt(Libs.room_compiler)
	implementation(Libs.room_ktx)

	// Squareup
	implementation(Libs.logging_interceptor)

	// Firebase TODO change to BOM
	implementation(Libs.firebase_core)
	implementation(Libs.firebase_crashlytics)
	implementation(Libs.firebase_messaging_ktx)

	implementation(Libs.work_runtime_ktx)

	kapt(Libs.moshi_kotlin_codegen)

	// Test
	testImplementation("com.htec.bojanb.core:test:1.0.0")
	testImplementation(Libs.robolectric)
	testImplementation(Libs.core_testing)
	testImplementation(Libs.core_ktx)
	testImplementation(Libs.kotlinx_coroutines_test)
	testImplementation(Libs.junit_ktx)
	testImplementation(Libs.mockk)
	testImplementation(Libs.kluent_android)

	// (Required) Writing and executing Unit Tests on the JUnit Platform
	testImplementation(Libs.junit_jupiter_api)
	testRuntimeOnly(Libs.junit_jupiter_engine)

	// (Optional) If you need "Parameterized Tests"
	testImplementation(Libs.junit_jupiter_params)

	// (Optional) If you also have JUnit 4-based tests
	//testImplementation(Libs.junit)
	testRuntimeOnly(Libs.junit_vintage_engine)
}