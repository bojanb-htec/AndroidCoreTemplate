import org.gradle.api.Action
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository

object Repositories {
	const val jitpack = "https://jitpack.io"
}

fun RepositoryHandler.jitpack(): MavenArtifactRepository =
	maven { setUrl(Repositories.jitpack) }

