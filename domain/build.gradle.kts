import org.gradle.kotlin.dsl.maven

plugins {
	id(Plugins.androidLibrary)
	kotlin(Plugins.android)
	kotlin(Plugins.kapt)
	id(Plugins.junit5)
	id(Plugins.hilt)
}

repositories {
	maven {
		url = uri("file:///Users/bojan/TMP/CoreMaven/LocalMvnRepo")
	}
}

android {
	namespace = "___PACKAGE_NAME___.domain"
	compileSdk = Config.Core.compileSdkVersion

	defaultConfig {
		minSdk = Config.Core.minSdkVersion
		targetSdk = Config.Core.targetSdkVersion

		testInstrumentationRunner = Config.Core.instrumentationRunner
		consumerProguardFiles("consumer-rules.pro")
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

//apply(plugin = "maven")

dependencies {
	api("com.htec.bojanb.core:domain:1.0.0")

	// Hilt
	implementation(Libs.hilt_android)
	kapt(Libs.hilt_android_compiler)
	kapt(Libs.hilt_compiler)

	// Coroutines/Flows
	implementation(Libs.kotlinx_coroutines_core)
	implementation(Libs.kotlinx_coroutines_android)

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

repositories {
	mavenCentral()
}