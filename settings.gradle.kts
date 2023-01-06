plugins {
	// See https://jmfayard.github.io/refreshVersions
	id("de.fayard.refreshVersions") version "0.40.0"
}

refreshVersions {
	enableBuildSrcLibs()
}

include(":domain", ":data", ":presentation", ":app")
