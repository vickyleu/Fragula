
plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}



android {
    compileSdk = 33

//    group = ""
//    version = "2.6"
    namespace = "com.fragula2.compose"

    defaultConfig {
        minSdk = 23
//        targetSdk = 33
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    sourceSets {
        named("main") {
            java.srcDir("src/main/kotlin")
        }
    }
    buildFeatures {
        compose = true
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
                groupId = "com.fragula2"
                artifactId = "compose"
                version = "2.7.0"
            }
            // Creates a Maven publication called “debug”.
            register("debug", MavenPublication::class) {
                // Applies the component for the release build variant.
                from(components["debug"])
                // You can then customize attributes of the publication as shown below.
                groupId = "com.fragula2"
                artifactId = "compose"
                version = "2.7.0"
            }
        }
    }
}

dependencies {
    // Core
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.21")
    // Compose
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.foundation:foundation:1.4.3")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    // Common
//    implementation("com.fragula2:fragula-common:2.7")
    api(project(":fragula-common"))
}