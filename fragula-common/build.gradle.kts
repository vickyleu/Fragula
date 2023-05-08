plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

android {
    compileSdk = 33

    namespace = "com.fragula2.common"

    defaultConfig {
        minSdk = 23
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    sourceSets {
        named("main") {
            java.srcDir("src/main/kotlin")
        }
    }
}
// Because the components are created only during the afterEvaluate phase, you must
// configure your publications using the afterEvaluate() lifecycle method.
afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            register("release", MavenPublication::class) {
                // Applies the component for the release build variant.
                from(components["release"])
                // You can then customize attributes of the publication as shown below.
                groupId = "com.github.vickyleu"
                artifactId = "fragula-common"
                version = "2.7.0"
            }
            // Creates a Maven publication called “debug”.
            register("debug", MavenPublication::class) {
                // Applies the component for the release build variant.
                from(components["debug"])
                // You can then customize attributes of the publication as shown below.
                groupId = "com.github.vickyleu"
                artifactId = "fragula-common"
                version = "2.7.0"
            }
        }
    }
}

dependencies {
    // Core
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.21")
}