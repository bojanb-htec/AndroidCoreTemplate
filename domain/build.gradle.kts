plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.com.google.dagger.hilt.android)
	alias(libs.plugins.com.google.developers.ksp)
	alias(libs.plugins.android.junit5)
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
}

dependencies {
	api(platform(libs.htecgroup.androidcore.bom))
	api(libs.htecgroup.androidcore.domain)

	// Hilt
	implementation(libs.dagger.hilt)
	ksp(libs.dagger.hilt.compiler)

	// Coroutines/Flows
	implementation(libs.kotlinx.coroutines.core)
	implementation(libs.kotlinx.coroutines.android)

	
	testImplementation(libs.htecgroup.androidcore.test)

	testImplementation(libs.robolectric)
	testImplementation(libs.androidx.core.testing)
	testImplementation(libs.core.ktx)
	testImplementation(libs.kotlinx.coroutines.test)
	testImplementation(libs.androidx.junit.ktx)
	testImplementation(libs.mockk)
	testImplementation(libs.kluent.android)

	// (Required) Writing and executing Unit Tests on the JUnit Platform
	testImplementation(libs.junit.jupiter.api)
	testImplementation(libs.junit.jupiter.engine)

	// (Optional) If you need "Parameterized Tests"
	testImplementation(libs.junit.jupiter.params)

	// (Optional) If you also have JUnit 4-based tests
//	testImplementation(libs.junit)
	testImplementation(libs.junit.vintage.engine)

}

repositories {
	mavenCentral()
}