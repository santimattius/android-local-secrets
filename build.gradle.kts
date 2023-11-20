// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.google.secrets.gradle.plugin) apply false
}
true // Needed to make the Suppress annotation work for the plugins block

buildscript {
    dependencies {
        classpath(libs.dep.google.secrets.gradle.plugin)
    }
}