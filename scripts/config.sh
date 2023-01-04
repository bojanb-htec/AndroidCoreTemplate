#!/bin/sh

# Replacing PACKAGE_NAME placeholder with provided package name
echo "Enter package name:"
read package
packagePlaceholder="___PACKAGE_NAME___"

# Find and replace the string in all files under the search directory, ignoring .git directories
LC_ALL=C find "$PWD" -not -path '*/.git/*' -not -path '*/scripts/*' -type f -exec sed -i '' "s/$packagePlaceholder/$package/g" {} +


# Move directories to new package name
packagePath=${package//./\\\/}
packagePathPlaceholder="___PACKAGE_PATH___"

# Find all directories with the packagePathPlaceholder in the name
for dir in $(find . -type d -path "*$packagePathPlaceholder"); do
  # Construct the new directory name by replacing packagePathPlaceholder with packagePath
  new_dir=$(echo $dir | sed "s/$packagePathPlaceholder/$packagePath/g")
  # Create the new directory
  mkdir -p "$new_dir"
  # Move all files from the old directory to the new one
  mv "$dir"/* "$new_dir"
  # Remove the old directory
  rmdir "$dir"
done


# Replacing App Name with provided input
echo "Enter app name:"
read name
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
