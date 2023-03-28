import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.Properties

object Signing {

	var keystoreProperties = Properties()

	fun loadReleaseProperties(project: Project) {
		try {
			val keystorePropertiesFile = project.file("signing/keystore.properties")
			keystoreProperties = Properties()
			keystoreProperties.load(FileInputStream(keystorePropertiesFile))
		} catch (e: FileNotFoundException) {
			print("Not found keystore.properties file. It should be located under the app/signing directory")
		}
	}

	val keyAlias get() = keystoreProperties["keyAlias"] as String
	val keyPassword get() = keystoreProperties["keyPassword"] as String
	fun getStoreFile(project: Project) = project.file(keystoreProperties["storeFile"] as String)
	val storePassword get() = keystoreProperties["storePassword"] as String

}