plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.org.jetbrains.kotlin.kapt)
	alias(libs.plugins.com.google.dagger.hilt.android)
	alias(libs.plugins.kotlin.parcelize)
	alias(libs.plugins.android.junit5)
	alias(libs.plugins.androidx.navigation.safeargs)
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
		dataBinding = true
		viewBinding = true
	}
}

dependencies {

	api(platform(libs.htecgroup.androidcore.bom))
	api(libs.htecgroup.androidcore.presentation.databinding)

	implementation(project(Config.Module.domain))

	// Hilt
	implementation(libs.dagger.hilt)
	kapt(libs.dagger.hilt.compiler)


	implementation(libs.androidx.constraintlayout)

	implementation(libs.play.services.ads)

	implementation(libs.androidx.work.runtime.ktx)

	implementation(libs.androidx.swiperefreshlayout)

	// Test
	testImplementation(project(Config.Test.moduleName))
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
	//testImplementation(Libs.junit)
	testRuntimeOnly(libs.junit.vintage.engine)

	// Android Test

	androidTestImplementation(libs.androidx.runner)
	androidTestImplementation(libs.androidx.rules)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(libs.androidx.espresso.contrib)
}