@Suppress("DSL_SCOPE_VIOLATION")
plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.android.kotlin)
	alias(libs.plugins.kapt)
	alias(libs.plugins.hilt)
	alias(libs.plugins.kotlin.serialization)
}

android {
	namespace = "com.liam.liamflix"
	compileSdk = libs.versions.compileSdk.get().toInt()

	defaultConfig {
		applicationId = "com.liam.liamflix"
		minSdk = libs.versions.minSdk.get().toInt()
		targetSdk = libs.versions.targetSdk.get().toInt()
		versionCode = libs.versions.versionCode.get().toInt()
		versionName = libs.versions.versionName.get()

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlinOptions {
		jvmTarget = libs.versions.jvmTarget.get()
	}
	buildFeatures {
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtensionVersion.get()
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

// Allow references to generated code
kapt {
	correctErrorTypes = true
}

dependencies {
	implementation(libs.bundles.android.library)

	implementation(platform(libs.compose.bom))
	implementation(libs.bundles.compose.library)

	implementation(libs.bundles.hilt)
	kapt(libs.bundles.hilt.compiler)

	implementation(libs.bundles.network)
	implementation(libs.bundles.json.parser)

	testImplementation(libs.bundles.test)
	androidTestImplementation(libs.bundles.android.test)

	androidTestImplementation(platform(libs.compose.bom))
	androidTestImplementation(libs.bundles.android.compose.test)
	debugImplementation(libs.bundles.compose.debug)
}