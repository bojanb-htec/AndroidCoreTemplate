import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {

	repositories {
		google()
		mavenCentral()
		mavenLocal()
	}
	dependencies {
		classpath(Libs.com_android_tools_build_gradle)
		classpath(Libs.kotlin_gradle_plugin)
		classpath(Libs.google_services)
		// only-for-crashlytics: classpath(Libs.firebase_crashlytics_gradle)
		classpath(Libs.android_junit5)
		// only-for-app-distribution: classpath(Libs.firebase_appdistribution_gradle) {
		// only-for-app-distribution: 	// Conflicting versions with refreshVersions plugin
		// only-for-app-distribution: 	exclude(group = "com.google.guava", module = "guava")
		// only-for-app-distribution: }
		classpath(Libs.hilt_android_gradle_plugin)
		classpath(Libs.navigation_safe_args_gradle_plugin)
	}
}

plugins {
	id(Plugins.detekt).version(Libs.io_gitlab_arturbosch_detekt_gradle_plugin)
}

allprojects {
	repositories {
		google()
		mavenCentral()
		mavenLocal()
		jitpack()
	}
}

tasks.wrapper {
	gradleVersion = "7.5.1"
	distributionType = Wrapper.DistributionType.ALL
}

tasks.withType<KotlinCompile>().all {
	kotlinOptions {
		jvmTarget = Config.___APP_NAME_CAMEL___.javaVersion.toString()
	}
}

tasks.register("clean", Delete::class) {
	delete(rootProject.buildDir)
}