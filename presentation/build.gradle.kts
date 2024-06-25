plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.com.google.dagger.hilt.android)
	alias(libs.plugins.com.google.developers.ksp)
	alias(libs.plugins.kotlin.parcelize)
	alias(libs.plugins.android.junit5)
}

apply(from = Config.___APP_NAME_CAMEL___.detekt)

android {
  namespace = "${Config.___APP_NAME_CAMEL___.applicationId}.presentation"
	compileSdk = Config.___APP_NAME_CAMEL___.compileSdkVersion

	defaultConfig {
		minSdk = Config.___APP_NAME_CAMEL___.minSdkVersion
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
		kotlinCompilerExtensionVersion = libs.versions.compiler.get()
	}
}

dependencies {
	api(platform(libs.htecgroup.androidcore.bom))
	api(libs.htecgroup.androidcore.presentation)

	implementation(project(Config.Module.domain))

	// Compose
	implementation(libs.androidx.runtime)
	implementation(libs.androidx.lifecycle.viewmodel.compose)
	implementation(libs.androidx.hilt.navigation.compose)
	implementation(libs.androidx.navigation.compose)
	implementation(libs.androidx.activity.compose)

	// Compose BOM
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.ui.tooling)
	implementation(libs.androidx.material3)

	// Hilt
	implementation(libs.dagger.hilt)
	ksp(libs.dagger.hilt.compiler)

	implementation(libs.play.services.ads)

	implementation(libs.androidx.work.runtime.ktx)

	// Test
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

	// Android Test

	androidTestImplementation(libs.androidx.runner)
	androidTestImplementation(libs.androidx.rules)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(libs.androidx.espresso.contrib)
}
