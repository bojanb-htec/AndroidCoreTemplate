#!/bin/sh

setupPushNotifications() {
    echo "Setting up push notifications..."
    # Uncomment // only-for-push-notifications: lines
    LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e 's/\/\/ only-for-push-notifications\: //g' {} +
}

removePushNotificationsSupport() {
    echo "Removing push notification support..."
    # Removing unnecessary files and folders
    rm -rf ./app/src/main/java/$packagePath/app/di/NotificationModule.kt
    rm -rf ./data/src/main/java/$packagePath/data/notification
    rm -rf ./data/src/main/java/$packagePath/data/repositories/contracts/INotificationTokenStore.kt
    rm -rf ./domain/src/main/java/$packagePath/domain/service/NotificationController.kt
    rm -rf ./presentation/src/main/java/$packagePath/presentation/utils/notification

    # remove all lines containing comment // only-for-push-notifications:
    LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e '/\/\/ only-for-push-notifications\:/d' {} +
}

setupFirebaseAnalytics() {
    echo "Setting up analytics..."
    # Uncomment // only-for-analytics: lines
    LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e 's/\/\/ only-for-analytics\: //g' {} +
}

removeFirebaseAnalyticsSupport() {
    echo "Removing analytics support..."
    # Removing unnecessary files and folders
    rm -rf ./app/src/main/java/$packagePath/app/di/AnalyticsModule.kt
    rm -rf ./app/src/main/java/$packagePath/app/infrastructure/AnalyticsParamImpl.kt
    rm -rf ./app/src/main/java/$packagePath/app/infrastructure/FirebaseAnalyticsImpl.kt
    rm -rf ./domain/src/main/java/$packagePath/domain/service/analytics

    # remove all lines containing comment // only-for-analytics:
    LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e '/\/\/ only-for-analytics\:/d' {} +
}

setupCrashlytics() {
    echo "Setting up Crashlytics..."
    # Uncomment // only-for-crashlytics: lines
    LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e 's/\/\/ only-for-crashlytics\: //g' {} +
}

removeCrashlyticsSupport() {
    echo "Removing Crashlytics support..."
    # Removing unnecessary files and folders
    rm -rf ./app/src/main/java/$packagePath/app/di/CrashlyticsModule.kt
    rm -rf ./app/src/main/java/$packagePath/app/infrastructure/FirebaseCrashlyticsImpl.kt

    # remove all lines containing comment // only-for-crashlytics:
    LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e '/\/\/ only-for-crashlytics\:/d' {} +
}

setupAppDistribution() {
    echo "Setting up App Distribution..."
    # Uncomment // only-for-app-distribution: lines
    LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e 's/\/\/ only-for-app-distribution\: //g' {} +
}

removeAppDistributionSupport() {
    echo "Removing App Distribution support..."
    # remove all lines containing comment // only-for-app-distribution:
    LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e '/\/\/ only-for-app-distribution\:/d' {} +
}

setupGoogleAds() {
    echo "Setting up Google Ads..."
    # Uncomment // only-for-google-ads: lines
    LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e 's/\/\/ only-for-google-ads\: //g' {} +
}

removeGoogleAdsSupport() {
    echo "Removing Google Ads support..."
    # remove all lines containing comment // only-for-google-ads:
    LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e '/\/\/ only-for-google-ads\:/d' {} +
}


addGoogleServices() {

    # Uncomment // only-for-google-services: lines
    LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e 's/\/\/ only-for-google-services\: //g' {} +

    echo "NOTE: Don't forget to check Firebase steps and to add google-service.json file in project"

    # Setup push notifications
    read -p "Do you want setup Firebase push notifications? [YES]" usePushNotifications
    usePushNotifications=${usePushNotifications:-YES}

    case "$usePushNotifications" in
        [yY][eE][sS]|[yY])
            setupPushNotifications
            ;;
        [nN][oO]|[nN])
            removePushNotificationsSupport
            ;;
        *)
            echo "Invalid input."
            ;;
    esac

    # Setup analytics
    read -p "Do you want setup Firebase analytics? [YES]" useAnalytics
    useAnalytics=${useAnalytics:-YES}

    case "$useAnalytics" in
        [yY][eE][sS]|[yY])
            setupFirebaseAnalytics
            ;;
        [nN][oO]|[nN])
            removeFirebaseAnalyticsSupport
            ;;
        *)
            echo "Invalid input."
            ;;
    esac

    # Setup Crashlytics
    read -p "Do you want setup Crashlytics? [YES]" useCrashlytics
    useCrashlytics=${useCrashlytics:-YES}

    case "$useCrashlytics" in
        [yY][eE][sS]|[yY])
            setupCrashlytics
            ;;
        [nN][oO]|[nN])
            removeCrashlyticsSupport
            ;;
        *)
            echo "Invalid input."
            ;;
    esac

    # Setup App Distribution
    read -p "Do you want setup Firebase App Distribution? [YES]" useAppDistribution
    useAppDistribution=${useAppDistribution:-YES}

    case "$useAppDistribution" in
        [yY][eE][sS]|[yY])
            setupAppDistribution
            ;;
        [nN][oO]|[nN])
            removeAppDistributionSupport
            ;;
        *)
            echo "Invalid input."
            ;;
    esac

    # Setup Google Ads (MobileAds)
    read -p "Do you want setup Google Ads (MobileAds)? [YES]" useGoogleAds
    useGoogleAds=${useGoogleAds:-YES}

    case "$useGoogleAds" in
        [yY][eE][sS]|[yY])
            setupGoogleAds
            ;;
        [nN][oO]|[nN])
            removeGoogleAdsSupport
            ;;
        *)
            echo "Invalid input."
            ;;
    esac
}

removeGoogleServicesSupport() {
    echo "Removing Google services support..."

    # remove all lines containing comment // only-for-google-services:
    LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e '/\/\/ only-for-google-services\:/d' {} +

    removePushNotificationsSupport
    removeFirebaseAnalyticsSupport
    removeCrashlyticsSupport
    removeAppDistributionSupport
    removeGoogleAdsSupport
}



# Setup Google Services
read -p "Do you want use Google serivces? [YES]" useGoogleServices
useGoogleServices=${useGoogleServices:-YES}

case "$useGoogleServices" in
    [yY][eE][sS]|[yY])
        addGoogleServices
        ;;
    [nN][oO]|[nN])
        removeGoogleServicesSupport
        ;;
    *)
        echo "Invalid input."
        ;;

esac