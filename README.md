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

