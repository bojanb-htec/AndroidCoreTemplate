import java.io.File
import java.util.*

interface Credentials {
	val username: String
	val password: String
}

class MavenCredentials(dir: File) : Credentials {

	private val mavenProperties = readProperties(File("$dir/maven.properties"))
	override val username = mavenProperties["username"].toString()
	override val password = mavenProperties["password"].toString()

	private fun readProperties(propertiesFile: File) = Properties().apply {
		if (!propertiesFile.exists()) {
			propertiesFile.createNewFile()
			put("username", "")
			put("password", "")
			propertiesFile.outputStream().use { fos ->
				store(fos, null)
			}
		}
		propertiesFile.inputStream().use { fis ->
			load(fis)
		}
	}
}