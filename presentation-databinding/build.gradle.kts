plugins {
	id(Plugins.androidLibrary)
	kotlin(Plugins.android)
	kotlin(Plugins.kapt)
	id(Plugins.junit5)
	id(Plugins.hilt)
	id(Plugins.kotlinParcelize)
	id(Plugins.androidxNavigationSafeargs)
}

apply(from = Config.___APP_NAME___.detekt)

android {
	compileSdk = Config.___APP_NAME___.compileSdkVersion

	defaultConfig {
		minSdk = Config.___APP_NAME___.minSdkVersion
		targetSdk = Config.___APP_NAME___.targetSdkVersion
		testInstrumentationRunner = Config.___APP_NAME___.instrumentationRunner
		consumerProguardFiles("consumer-rules.pro")
	}

	buildTypes {
		getByName(Config.___APP_NAME___.release) {
			isMinifyEnabled = Config.___APP_NAME___.minifyEnabled
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
		abortOnError = false
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

	api(platform(Libs.core_bom))
	api(Libs.presentation_databinding)

	implementation(project(Config.Module.domain))

	// Hilt
	implementation(Libs.hilt_android)
	kapt(Libs.hilt_android_compiler)


	implementation(Libs.constraintlayout)

	implementation(Libs.play_services_ads)

	implementation(Libs.work_runtime_ktx)

	implementation(Libs.swiperefreshlayout)

	// Test
	testImplementation(platform(Libs.core_bom))
	testImplementation(Libs.test)
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