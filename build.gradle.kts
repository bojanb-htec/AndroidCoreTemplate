import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // only-for-crashlytics: alias(libs.plugins.com.google.firebase.crashlytics) apply false
    // only-for-app-distribution: alias(libs.plugins.com.google.firebase.appdistribution) apply false
    // only-for-google-services: alias(libs.plugins.com.google.gms.google.services) apply false
    alias(libs.plugins.androidx.navigation.safeargs) apply false

    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.com.google.dagger.hilt.android) apply false
    alias(libs.plugins.org.jetbrains.kotlin.kapt) apply false
    alias(libs.plugins.com.google.developers.ksp) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.android.junit5) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false

    alias(libs.plugins.io.gitlab.arturbosch.detekt)
}

tasks.wrapper {
	gradleVersion = libs.versions.agp.get()
	distributionType = Wrapper.DistributionType.ALL
}

tasks.withType<KotlinCompile>().all {
	kotlinOptions {
		jvmTarget = Config.___APP_NAME_CAMEL___.javaVersion.toString()
	}
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}