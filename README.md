# Andromeda-Core ![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white) ![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)

A library for using pre-built codes

> ![GitHub repo size](https://img.shields.io/github/repo-size/farsroidx/andromeda-core)

### Installation:

#### 1. Add Jitpack Maven:

##### in `settings.gradle`:
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        .
        .
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
        .
        .
        ------> maven(url = "https://jitpack.io")
    }
}
```

#### 2. Copy the following line in section `dependencies` in file `build.gradle` of module `app` and replace it with `LATEST_VERSION` according to the latest version in the repository:

### LATEST_VERSION: [![](https://jitpack.io/v/farsroidx/andromeda-core.svg)](https://jitpack.io/#farsroidx/andromeda-core)

##### in `build.gradle`:
```groovy
dependencies {
    implementation 'com.github.farsroidx:andromeda-core:🔝LATEST_VERSION🔝'
}
```

##### in `build.gradle.kts`:
```kotlin
dependencies {
    implementation("com.github.farsroidx:andromeda-core:🔝LATEST_VERSION🔝")
}
```

#### 3. Adding the following rules to use R8 and proguard in `proguard-rules.pro`:

```pro
-dontwarn java.lang.invoke.StringConcatFactory

# Keep all classes and interfaces in ir.farsroidx.m31 package
-keep class ir.farsroidx.m31.** { *; }

# Keep all fields and methods in ir.farsroidx.m31 package
-keepclassmembers class ir.farsroidx.m31.** { *; }

# Keep all extension functions in ir.farsroidx.m31 package
-keepclassmembers class ir.farsroidx.m31.** {
    public <methods>;
}




-dontwarn java.lang.invoke.StringConcatFactory

# Keep all classes and interfaces in ir.farsroidx.m31 package
-keep class ir.farsroidx.m31.** { *; }

# Keep all fields and methods in ir.farsroidx.m31 package
-keepclassmembers class ir.farsroidx.m31.** { *; }

# If using reflection, keep the names of the fields in ir.farsroidx.m31 package
-keepclassmembers class ir.farsroidx.m31.** {
    <fields>;
}

# If using reflection, keep the names of the methods in ir.farsroidx.m31 package
-keepclassmembers class ir.farsroidx.m31.** {
    <methods>;
}

# Keep all extension functions in ir.farsroidx.m31 package
-keepclassmembers class ir.farsroidx.m31.** {
    public <methods>;
}

# Keep all public and protected fields and methods in ir.farsroidx.m31 package
-keepclassmembers class ir.farsroidx.m31.** {
    public protected <fields>;
    public protected <methods>;
}

# Keep all public and protected classes and interfaces in ir.farsroidx.m31 package
-keep class ir.farsroidx.m31.** {
    public protected *;
}





# Ignore warnings about java.lang.invoke.StringConcatFactory
-dontwarn java.lang.invoke.StringConcatFactory

# Keep all classes and interfaces in ir.farsroidx.m31 package
-keep class ir.farsroidx.m31.** { *; }

# Keep all fields, methods, and variables in ir.farsroidx.m31 package
-keepclassmembers class ir.farsroidx.m31.** { *; }

# Keep all extension functions in ir.farsroidx.m31 package
-keepclassmembers class ir.farsroidx.m31.** {
    public protected <methods>;
    public protected <fields>;
}

# Preserve Kotlin metadata
-keep class kotlin.Metadata { *; }
-keep class kotlin.jvm.internal.* { *; }

```

[![Ask Me Anything !](https://img.shields.io/badge/Ask%20me-anything-1abc9c.svg)](https://github.com/farsroidx)