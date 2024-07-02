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

# Get the directory of the current script
CURRENT_DIR=$(dirname "$0")

"$CURRENT_DIR/config-helper/google-services-config.sh"