pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal()
        mavenCentral()
        google()
        maven(url = "https://jitpack.io")
        maven(url = "https://plugins.gradle.org/m2/")
    }
}

rootProject.name = "Andromeda"

include(":app")

include(":m31-views-additives")
include(":m31-api")
include(":m31-cache-file")
include(":m31-compose")
include(":m31-compose-additives")
include(":m31-database")
include(":m31-exception-ui")
include(":m31-preferences")
include(":m31-socket-io")
include(":m31-views")
