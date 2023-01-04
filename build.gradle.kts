import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {

	repositories {
		google()
		mavenCentral()
	}
	dependencies {
		classpath(Libs.com_android_tools_build_gradle)
		classpath(Libs.kotlin_gradle_plugin)
		classpath(Libs.google_services)
		classpath(Libs.firebase_crashlytics_gradle)
		classpath(Libs.android_junit5)
		classpath(Libs.firebase_appdistribution_gradle) {
			// Conflicting versions with refreshVersions plugin
			exclude(group = "com.google.guava", module = "guava")
		}
		classpath(Libs.hilt_android_gradle_plugin)
		classpath(Libs.navigation_safe_args_gradle_plugin)
		classpath("com.github.dcendents:android-maven-gradle-plugin:2.1")

	}
}

plugins {
	id(Plugins.detekt).version(Libs.io_gitlab_arturbosch_detekt_gradle_plugin)
	id(Plugins.dokka).version(Libs.org_jetbrains_dokka_gradle_plugin)
}

allprojects {
	repositories {
		google()
		mavenCentral()
		jitpack()
		htec()
		htecSnapshots()
		maven {
			url = uri("file:///Users/bojan/TMP/CoreMaven/LocalMvnRepo")
		}
	}
}

tasks.wrapper {
	gradleVersion = "7.3.3"
	distributionType = Wrapper.DistributionType.ALL
}

tasks.withType<KotlinCompile>().all {
	kotlinOptions {
		jvmTarget = JavaVersion.VERSION_11.toString()
	}
}

tasks.register("clean", Delete::class) {
	delete(rootProject.buildDir)
}

/*tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}*/
