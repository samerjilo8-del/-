plugins {
    id "com.android.application"
    id "org.jetbrains.kotlin.android"
    id "kotlin-kapt"
}

android {
    namespace "com.talp.smartteacher"
    compileSdk 34

    defaultConfig {
        applicationId "com.talp.smartteacher"
        minSdk 24
        targetSdk 34
        versionCode 1
        versionName "1.0.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary true
        }
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_17
        targetCompatibility JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = '17'
    }
    buildFeatures {
        compose true
    }
    composeOptions {
        kotlinCompilerExtensionVersion '1.5.0'
    }
    packagingOptions {
        resources {
            excludes += '/META-INF/{AL2.0,LGPL2.1}'
        }
    }
}

dependencies {
    // Core Android
    implementation 'androidx.core:core-ktx:1.12.0'
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.6.2'
    implementation 'androidx.activity:activity-compose:1.8.0'
    
    // Jetpack Compose
    implementation 'androidx.compose.ui:ui:1.5.4'
    implementation 'androidx.compose.ui:ui-graphics:1.5.4'
    implementation 'androidx.compose.ui:ui-tooling-preview:1.5.4'
    implementation 'androidx.compose.material3:material3:1.1.2'
    implementation 'androidx.compose.material:material-icons-extended:1.5.4'
    implementation 'androidx.navigation:navigation-compose:2.7.5'
    
    // Room Database
    implementation 'androidx.room:room-runtime:2.6.1'
    implementation 'androidx.room:room-ktx:2.6.1'
    kapt 'androidx.room:room-compiler:2.6.1'
    
    // DataStore
    implementation 'androidx.datastore:datastore-preferences:1.0.0'
    
    // WorkManager
    implementation 'androidx.work:work-runtime-ktx:2.8.1'
    
    // Notifications
    implementation 'androidx.core:core:1.12.0'
    
    // ViewModel and StateFlow
    implementation 'androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2'
    
    // Hilt Dependency Injection (Optional but recommended)
    implementation 'com.google.dagger:hilt-android:2.47'
    kapt 'com.google.dagger:hilt-compiler:2.47'
    
    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3'
    
    // JSON Serialization
    implementation 'com.google.code.gson:gson:2.10.1'
    
    // Testing
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
    androidTestImplementation 'androidx.compose.ui:ui-test-junit4:1.5.4'
    debugImplementation 'androidx.compose.ui:ui-tooling:1.5.4'
    debugImplementation 'androidx.compose.ui:ui-test-manifest:1.5.4'
}
