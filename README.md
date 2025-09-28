# Andromeda ![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white) ![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white) ![Upwork](https://img.shields.io/badge/UpWork-6FDA44?style=for-the-badge&logo=Upwork&logoColor=white)

A library for using pre-built codes

> Library Size: ~500kb

### Installation:

#### 1. Add Jitpack Maven:

##### in `settings.gradle`:
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
        google()
        mavenCentral()
------> maven { url 'https://jitpack.io' }
    }
}
```

##### in `settings.gradle.kts`:
```kotlin
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal()
        mavenCentral()
        google()
------> maven(url = "https://jitpack.io")
    }
}
```

#### 2. Copy the following line in section `dependencies` in file `build.gradle` of module `app` and replace it with `LATEST_VERSION` according to the latest version in the [![](https://jitpack.io/v/farsroidx/andromeda.svg)](https://jitpack.io/#farsroidx/andromeda) repository:

##### in `build.gradle`:
```groovy
dependencies {
    implementation 'com.github.farsroidx:andromeda:LATEST_VERSION'
}
```

##### in `build.gradle.kts`:
```groovy
dependencies {
    implementation("com.github.farsroidx:andromeda:LATEST_VERSION")
}
```

[![Ask Me Anything !](https://img.shields.io/badge/Ask%20me-anything-1abc9c.svg)](https://github.com/riazati75)

### Change List

#### `v1.0.0` :
- **Added**:
    - ExceptionUi
    - Dispatchers
    - Memory
    - Preference