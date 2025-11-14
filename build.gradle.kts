// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // These aliases are pulled from the libs.versions.toml file
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.ksp) apply false
}
