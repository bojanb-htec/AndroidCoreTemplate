plugins {
	// See https://jmfayard.github.io/refreshVersions
	id("de.fayard.refreshVersions") version "0.51.0"
}

refreshVersions {
	enableBuildSrcLibs()
	rejectVersionIf {
		candidate.stabilityLevel.isLessStableThan(current.stabilityLevel)
	}
}

include(
	":app",
	":data",
	":domain",
	":presentation",
	":presentation-databinding"
)