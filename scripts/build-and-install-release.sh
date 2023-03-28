./gradlew clean
./gradlew :app:assembleRelease

# install the app on all attached devices and running emulators
adb devices | tail -n +2 | cut -sf 1 | xargs -I {} adb -s {} install -r ./app/build/outputs/apk/release/app-release.apk
