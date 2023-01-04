./gradlew clean
./gradlew :app:assembleDebug

# install the app on all attached devices and running emulators
adb devices | tail -n +2 | cut -sf 1 | xargs -I {} adb -s {} install -r ./app/build/outputs/apk/debug/app-debug.apk
