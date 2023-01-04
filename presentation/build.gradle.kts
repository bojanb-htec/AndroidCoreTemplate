plugins {
	id(Plugins.androidLibrary)
	kotlin(Plugins.android)
	kotlin(Plugins.kapt)
	id(Plugins.junit5)
	id(Plugins.hilt)
	id(Plugins.kotlinParcelize)
	id(Plugins.androidxNavigationSafeargs)
}

android {
	namespace = "___PACKAGE_NAME___.presentation"
	compileSdk = Config.SharedPref.compileSdkVersion

	defaultConfig {
		minSdk = Config.SharedPref.minSdkVersion
		targetSdk = Config.SharedPref.targetSdkVersion
		testInstrumentationRunner = Config.SharedPref.instrumentationRunner
		consumerProguardFiles("consumer-rules.pro")
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

	buildFeatures {
		dataBinding = true
		viewBinding = true
	}
}

dependencies {

	api("com.htec.bojanb.core:presentation:1.0.0")
	implementation(project(Config.Module.___APP_NAME_CAMEL___.domain))

	// Hilt
	implementation(Libs.hilt_android)
	kapt(Libs.hilt_android_compiler)
	kapt(Libs.hilt_compiler)



	implementation(AndroidX.recyclerView)
	implementation(AndroidX.navigation.fragmentKtx)
	implementation(AndroidX.navigation.uiKtx)
	implementation(AndroidX.multidex)
	implementation(Google.dagger.hilt.android)


	implementation(Libs.constraintlayout)

	implementation(Libs.play_services_ads)

	implementation(Libs.work_runtime_ktx)

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

	// Android Test

	androidTestImplementation(Libs.androidx_test_runner)
	androidTestImplementation(Libs.androidx_test_rules)
	androidTestImplementation(Libs.espresso_core)
	androidTestImplementation(Libs.espresso_contrib)
}