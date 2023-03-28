![HTEC Group](htec_logo.png)

# Android Core Template

This is repository template to configure Android Projects in HTEC.

This is a empty project prepared for the future projects. It uses CLEAN architecture and it imports HTEC Core domain, data, presentation and test libs.

Everything is preconfigured it is just required to run the setup.sh script located in scripts directory.

## Setup

Open terminal at root directory of the repository then run this script:
```
$ sh ./scripts/config.sh
```

Fill the required information (package name, app name etc.).

Open repository root directory in Android Studio and go to Preferences. Next go to Editor > File and Code Templates. Choose scheme Project instead of Default and fill free to use pre-configured file templates.


## IMPORTANT

To run Core Sample App until Core Lib is published, follow next steps:
- Clone [Core Lib][core],
- Run `./gradlew publish` inside the Core project,
- Switch to Sample project and run the app.

[core]: https://bitbucket.org/dusankosic/android-core/src/core/
