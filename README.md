# Android Local Secrets
Example using [google gradle plugin](https://github.com/google/secrets-gradle-plugin)

### Secrets Gradle Plugin for Android

A Gradle plugin for providing your secrets securely to your Android project.

This Gradle plugin reads secrets from a properties file not checked into version control, such as local.properties,
and expose those properties as variables in the Gradle-generated BuildConfig class and in the Android manifest file.

DISCLAIMER: This plugin is primarily for hiding your keys from version control. Since your key is part of the static binary, 
your API keys are still recoverable by decompiling an APK. So, securing your key using other measures like adding restrictions (if possible) are recommended.

#### Installation

Check this [documentation](https://github.com/google/secrets-gradle-plugin#installation)

## Add your env

Using local properties for define api key:

```properties
apiKey="{your-api-key}"
```

Using var:
```kotlin
val apiKey = BuildConfig.apiKey
```

Using into AndroidManifest:
```xml
<meta-data android:value="${apiKey}" />

```

### Last AGP versions
Add into gradle.properties:

```properties
android.defaults.buildfeatures.buildconfig=true
```

or into build.gradle.kts

```kotlin
android {
    buildFeatures {
        buildConfig = true
    }
}
```

**Important:** Avoid adding `android.defaults.buildfeatures.buildconfig=true` to your `gradle.properties` file because 
that property is [deprecated in AGP 8.0 and is scheduled to be removed in AGP 9.0](https://cs.android.com/android-studio/platform/tools/base/+/0bc1c23297760643b03e8cfd8acc52c007a99cd6).

## Github actions

```yml
# This is a basic workflow to help you get started with Actions
name: Checks
# Controls when the workflow will run
on:
  # Triggers the workflow on push or pull request events but only for the master branch
  push:
    branches: [ master ]
  pull_request:
    branches: [ master ]

  # Allows you to run this workflow manually from the Actions tab
  workflow_dispatch:

# A workflow run is made up of one or more jobs that can run sequentially or in parallel
jobs:
  keys:
    # The type of runner that the job will run on
    name: Tests
    runs-on: macos-latest

    # Steps represent a sequence of tasks that will be executed as part of the job
    steps:
      # Checks-out your repository under $GITHUB_WORKSPACE, so your job can access it
      - name: Checkout code
        uses: actions/checkout@v2

      - name: Set Up JDK
        uses: actions/setup-java@v1
        with:
          java-version: 17
      - name: Setup API Key
        env:
          APIKEY: ${{ secrets.APIKEY }}
        run: echo apiKey="$APIKEY" > ./local.properties
      - name: Set gradlew permissions
        run: chmod +x gradlew
      - name: Run Tests
        run: ./gradlew :app:check
```

