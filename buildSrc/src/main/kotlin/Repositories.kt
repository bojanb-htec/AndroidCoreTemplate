import org.gradle.api.Action
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository

object Repositories {

	const val jitpack = "https://jitpack.io"
	const val htec = "http://git.htec.rs:9081/nexus/content/repositories/android-release"
	const val htecSnapshots = "http://git.htec.rs:9081/nexus/content/repositories/android-snapshots"
}

fun RepositoryHandler.jitpack(): MavenArtifactRepository =
	maven { setUrl(Repositories.jitpack) }

fun RepositoryHandler.htec(action: Action<MavenArtifactRepository>? = null): MavenArtifactRepository =
	maven {
		name = "Release"
		setUrl(Repositories.htec)
		isAllowInsecureProtocol = true
		action?.execute(this)
	}

fun RepositoryHandler.htecSnapshots(action: Action<MavenArtifactRepository>? = null): MavenArtifactRepository =
	maven {
		name = "Snapshots"
		setUrl(Repositories.htecSnapshots)
		isAllowInsecureProtocol = true
		action?.execute(this)
	}

fun MavenArtifactRepository.authorize(credentials: Credentials) {
	credentials {
		username = credentials.username
		password = credentials.password
	}
}