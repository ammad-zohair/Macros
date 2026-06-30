pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Macros"
include(":app")
include(":core:shared")
include(":core:network")
include(":core:navigation")
include(":core:design-system")
include(":feature:splash:splash-api")
include(":feature:dashboard:dashboard-api")
include(":feature:splash:splash-impl")
include(":feature:dashboard:dashboard-impl")
