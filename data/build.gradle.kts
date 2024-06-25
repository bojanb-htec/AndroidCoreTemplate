plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.com.google.developers.ksp)
	alias(libs.plugins.android.junit5)
	alias(libs.plugins.com.google.dagger.hilt.android)
}

apply(from = Config.___APP_NAME_CAMEL___.detekt)

android {
	compileSdk = Config.___APP_NAME_CAMEL___.compileSdkVersion

	defaultConfig {
		minSdk = Config.___APP_NAME_CAMEL___.minSdkVersion
		targetSdk = Config.___APP_NAME_CAMEL___.targetSdkVersion

		testInstrumentationRunner = Config.___APP_NAME_CAMEL___.instrumentationRunner
		consumerProguardFiles("consumer-rules.pro")

		javaCompileOptions {
			annotationProcessorOptions {
				mutableMapOf("room.schemaLocation" to "$projectDir/schemas")

			}
		}
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

	buildFeatures {
		buildConfig = true
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
	implementation(platform(libs.htecgroup.androidcore.bom))
	implementation(libs.htecgroup.androidcore.data)

	implementation(project(Config.Module.domain))

	// Hilt
	implementation(libs.dagger.hilt)
	ksp(libs.dagger.hilt.compiler)

	// Room
	implementation(libs.androidx.room.runtime)
	ksp(libs.androidx.room.compiler)
	implementation(libs.androidx.room.ktx)

	// Squareup
	implementation(libs.logging.interceptor)

	implementation(platform(libs.firebase.bom))
	// only-for-crashlytics: implementation(libs.firebase.crashlytics.ktx)
	// only-for-analytics: implementation(libs.firebase.analytics.ktx)
	// only-for-push-notifications: implementation(libs.firebase.messaging.ktx)

	implementation(libs.androidx.work.runtime.ktx)

	ksp(libs.moshi.kotlin.codegen)

	// Test
	testImplementation(libs.htecgroup.androidcore.test)
	testImplementation(libs.robolectric)
	testImplementation(libs.androidx.core.testing)
	testImplementation(libs.androidx.core.ktx)

	testImplementation(libs.kotlinx.coroutines.test)
	testImplementation(libs.androidx.junit.ktx)
	testImplementation(libs.mockk)
	testImplementation(libs.kluent.android)

	// (Required) Writing and executing Unit Tests on the JUnit Platform
	testImplementation(libs.junit.jupiter.api)
	testRuntimeOnly(libs.junit.jupiter.engine)

	// (Optional) If you need "Parameterized Tests"
	testRuntimeOnly(libs.junit.jupiter.params)

	// (Optional) If you also have JUnit 4-based tests
	//testImplementation(Libs.junit)
	testRuntimeOnly(libs.junit.vintage.engine)
}