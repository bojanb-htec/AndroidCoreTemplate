import org.gradle.api.JavaVersion

object Config {

	object ___APP_NAME_CAMEL___ {
		const val applicationId = "___PACKAGE_NAME___"
		const val compileSdkVersion = 33
		const val minSdkVersion = 21
		const val targetSdkVersion = 33
		const val versionCode = 1
		const val versionName = "1.0.0"
		const val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		const val release = "release"
		const val debug = "debug"
		const val minifyEnabled = true
		const val multiDex = true
		const val detekt = "../detekt.gradle"
		val javaVersion = JavaVersion.VERSION_11
	}

	object Module {
		const val presentation = ":presentation"
		const val domain = ":domain"
		const val data = ":data"
		const val test = ":test"
	}
// only-for-app-distribution: 
// only-for-app-distribution: 	object FirebaseDistribution {
// only-for-app-distribution: 		const val groups = "htec"
// only-for-app-distribution: 		const val releaseNotesFile = "./app/release-notes.txt"
// only-for-app-distribution: 		// const val serviceCredentialsFile = "./app/app-name-firebase.json"
// only-for-app-distribution: 	}

	object Dev {
		const val name = "dev"
		const val appIdSuffix = ".dev"
	}

}