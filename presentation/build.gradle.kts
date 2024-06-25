import de.fayard.refreshVersions.core.versionFor

plugins {
	id(Plugins.androidLibrary)
	kotlin(Plugins.android)
	kotlin(Plugins.kapt)
	id(Plugins.junit5)
	id(Plugins.hilt)
	id(Plugins.kotlinParcelize)
}

apply(from = Config.___APP_NAME_CAMEL___.detekt)

android {
	compileSdk = Config.___APP_NAME_CAMEL___.compileSdkVersion

	defaultConfig {
		minSdk = Config.___APP_NAME_CAMEL___.minSdkVersion
		targetSdk = Config.___APP_NAME_CAMEL___.targetSdkVersion
		testInstrumentationRunner = Config.___APP_NAME_CAMEL___.instrumentationRunner
		consumerProguardFiles("consumer-rules.pro")
	}

	buildTypes {
		getByName(Config.___APP_NAME_CAMEL___.release) {
			isMinifyEnabled = Config.___APP_NAME_CAMEL___.minifyEnabled
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = Config.___APP_NAME_CAMEL___.javaVersion
		targetCompatibility = Config.___APP_NAME_CAMEL___.javaVersion
	}

	kotlinOptions {
		jvmTarget = Config.___APP_NAME_CAMEL___.javaVersion.toString()
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
		compose = true
	}

	composeOptions {
		kotlinCompilerExtensionVersion = versionFor(Libs.androidx_compose_compiler_compiler)
	}
}

dependencies {
	api(platform(Libs.core_bom))
	api(Libs.presentation)
	implementation(project(Config.Module.domain))

	// Compose
	implementation(Libs.material3)
	implementation(Libs.androidx_compose_runtime_runtime)
	implementation(Libs.lifecycle_viewmodel_compose)
	implementation(Libs.hilt_navigation_compose)
	implementation(Libs.navigation_compose)
	implementation(Libs.activity_compose)
	implementation(Libs.ui_tooling_preview)
	implementation(Libs.ui_tooling)
	implementation(Libs.accompanist_swiperefresh)

	// Hilt
	implementation(Libs.hilt_android)
	kapt(Libs.hilt_android_compiler)
	kapt(Libs.hilt_compiler)

	implementation(Libs.play_services_ads)

	implementation(Libs.work_runtime_ktx)

	// Test
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
