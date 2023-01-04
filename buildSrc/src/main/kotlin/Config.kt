import org.gradle.api.JavaVersion

object Config {

	object Core : MavenPublishingConfig {
		override val configName: String = "Core"
		const val applicationId = "___PACKAGE_NAME___"
		const val compileSdkVersion = 31
		const val minSdkVersion = 19
		const val targetSdkVersion = 31
		const val versionCode = 1
		override val versionName = "2.0.2"
		const val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		const val release = "release"
		const val debug = "debug"
		const val minifyEnabled = false
		const val multiDex = true
		const val detekt = "../detekt.gradle"
		const val moduleGraph = "../module-graph.gradle"
		override val group = "com.htec.android"
		override val artifactId = "core-kt"
		val javaVersion = JavaVersion.VERSION_11
	}

	object Module {
		object ___APP_NAME_CAMEL___ {
			const val presentation = ":presentation"
			const val domain = ":domain"
			const val data = ":data"
			const val test = ":test"
		}
	}

	object FirebaseDistribution {
		const val groups = "htec"
		const val releaseNotesFile = "./release-notes.txt"
		//        const val serviceCredentialsFile = "./project-name-firebase.json"
	}

	object Dev {
		const val name = "dev"
		const val dimension = "core"
		const val appIdSuffix = ".dev"
	}

	object SharedPref : MavenPublishingConfig {
		override val configName: String = "SharedPreferences"
		const val compileSdkVersion = 31
		const val minSdkVersion = 19
		const val targetSdkVersion = 31
		const val versionCode = 1
		override val versionName = "2.0.2"
		const val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		const val release = "release"
		const val minifyEnabled = false
		const val multiDex = true
		override val group = "com.htec.android"
		override val artifactId = "shared-preferences-kt"
	}

}