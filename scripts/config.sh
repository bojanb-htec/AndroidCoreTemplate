#!/bin/sh

# Replacing PACKAGE_NAME placeholder with provided package name
read -p "Enter package name:" package
packagePlaceholder="___PACKAGE_NAME___"

packagePath=${package//./\/}

# Find and replace the string in all files under the search directory, ignoring .git directories
LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' "s/$packagePlaceholder/$package/g" {} +


# Move directories to new package name
packagePathEscaping=${package//./\\\/}
packagePathPlaceholder="___PACKAGE_PATH___"

# Find all directories with the packagePathPlaceholder in the name
for dir in $(find . -type d -path "*$packagePathPlaceholder"); do
  # Construct the new directory name by replacing packagePathPlaceholder with packagePathEscaping
  new_dir=$(echo $dir | sed "s/$packagePathPlaceholder/$packagePathEscaping/g")
  # Create the new directory
  mkdir -p "$new_dir"
  # Move all files from the old directory to the new one
  mv "$dir"/* "$new_dir"
  # Remove the old directory
  rmdir "$dir"
done


# Replacing App Name with provided input
read -p "Enter app name:" name
appNamePlaceholder="___APP_NAME___"

# Find and replace the string in all files under the search directory, ignoring .git directories
LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' "s/$appNamePlaceholder/$name/g" {} +

# Convert to camel case
camelCaseAppName=""
for word in $(echo "$name" | tr '_' ' '); do
  camelCaseAppName="$camelCaseAppName$(echo "$word" | awk '{print toupper(substr($0,1,1)) substr($0,2)}')"
done

appNameCamelPlaceholder="___APP_NAME_CAMEL___"

# Find and replace the string in all files under the search directory, ignoring .git directories
LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' "s/$appNameCamelPlaceholder/$camelCaseAppName/g" {} +

# Find all files with the appNameCamelPlaceholder in the name
for file in $(find . -type f -name "*$appNameCamelPlaceholder*"); do
  # Construct the new file name by replacing appNameCamelPlaceholder with camelCaseAppName
  new_file=$(echo $file | sed "s/$appNameCamelPlaceholder/$camelCaseAppName/g")
  # Move all files from the old directory to the new one
  mv "$file" "$new_file"
done


# Chose presentation layer type
read -p "Do you want to use Compose? [YES]" useCompose
useCompose=${useCompose:-YES}

case "$useCompose" in
    [yY][eE][sS]|[yY])
        echo "Setting up Compose..."
        # remove presentation-databinding directory
        rm -rf ./presentation-databinding
        # remove all lines containing comment // only-for-databinding
        LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e '/\/\/ only-for-databinding\:/d' {} +
        ;;
    [nN][oO]|[nN])
        echo "Setting up databinding..."
        # replace the content of presentation directiry with
        # the content of presentation-databinding directory
        rm -rf data/src/
        mv ./presentation-databinding ./presentation
        # Uncomment // only-for-databinding: with empty string
        LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e 's/\/\/ only-for-databinding\: //g' {} +
        ;;
    *)
        echo "Invalid input."
        ;;
esac

# Setup push notifications
read -p "Do you want setup Firebase push notifications? [YES]" usePushNotifications
usePushNotifications=${usePushNotifications:-YES}

case "$usePushNotifications" in
    [yY][eE][sS]|[yY])
        echo "Setting up push notifications..."
        # Uncomment // only-for-push-notifications: lines
        LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e 's/\/\/ only-for-push-notifications\: //g' {} +
        ;;
    [nN][oO]|[nN])
        echo "Removing push notification support..."
        # Removing unnecessary files and folders
        rm -rf ./app/src/main/java/$packagePath/app/di/NotificationModule.kt
        rm -rf ./data/src/main/java/$packagePath/data/notification
        rm -rf ./data/src/main/java/$packagePath/data/repositories/contracts/INotificationTokenStore.kt
        rm -rf ./domain/src/main/java/$packagePath/domain/service/NotificationController.kt
        rm -rf ./presentation/src/main/java/$packagePath/presentation/utils/notification

        # remove all lines containing comment // only-for-push-notifications:
        LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e '/\/\/ only-for-push-notifications\:/d' {} +
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
        echo "Setting up analytics..."
        # Uncomment // only-for-analytics: lines
        LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e 's/\/\/ only-for-analytics\: //g' {} +
        ;;
    [nN][oO]|[nN])
        echo "Removing analytics support..."
        # Removing unnecessary files and folders
        rm -rf ./app/src/main/java/$packagePath/app/di/AnalyticsModule.kt
        rm -rf ./app/src/main/java/$packagePath/app/infrastructure/AnalyticsParamImpl.kt
        rm -rf ./app/src/main/java/$packagePath/app/infrastructure/FirebaseAnalyticsImpl.kt
        rm -rf ./domain/src/main/java/$packagePath/domain/service/analytics

        # remove all lines containing comment // only-for-analytics:
        LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e '/\/\/ only-for-analytics\:/d' {} +
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
        echo "Setting up Crashlytics..."
        # Uncomment // only-for-crashlytics: lines
        LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e 's/\/\/ only-for-crashlytics\: //g' {} +
        ;;
    [nN][oO]|[nN])
        echo "Removing Crashlytics support..."
        # Removing unnecessary files and folders
        rm -rf ./app/src/main/java/$packagePath/app/di/CrashlyticsModule.kt
        rm -rf ./app/src/main/java/$packagePath/app/infrastructure/FirebaseCrashlyticsImpl.kt

        # remove all lines containing comment // only-for-crashlytics:
        LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e '/\/\/ only-for-crashlytics\:/d' {} +
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
        echo "Setting up App Distribution..."
        # Uncomment // only-for-app-distribution: lines
        LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e 's/\/\/ only-for-app-distribution\: //g' {} +
        ;;
    [nN][oO]|[nN])
        echo "Removing App Distribution support..."
        # remove all lines containing comment // only-for-app-distribution:
        LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' -e '/\/\/ only-for-app-distribution\:/d' {} +
        ;;
    *)
        echo "Invalid input."
        ;;
esac