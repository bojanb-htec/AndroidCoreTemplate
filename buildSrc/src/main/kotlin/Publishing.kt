import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication

interface MavenPublishingConfig {
	val configName: String
	val artifactId: String
	val group: String
	val versionName: String
}

private fun Project.publication(componentName: String, config: MavenPublishingConfig) {
	afterEvaluate {
		extensions.configure("publishing", Action<PublishingExtension> {
			publications {
				create(config.configName, MavenPublication::class.java) {
					from(components.getByName(componentName))
					artifactId = config.artifactId
					groupId = config.group
					version = config.versionName
				}

				repositories {
					val credentials = MavenCredentials(this@afterEvaluate.rootDir)
					if (config.versionName.endsWith("SNAPSHOT")) {
						htecSnapshots {
							authorize(credentials)
						}
					} else {
						htec {
							authorize(credentials)
						}
					}
				}
			}
		})
	}
}

fun Project.releasePublication(config: MavenPublishingConfig) {
	publication("release", config)
}