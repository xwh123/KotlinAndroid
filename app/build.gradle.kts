plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

kapt {
    arguments {
        arg("AROUTER_MODULE_NAME", project.name)
    }
}

android {
    namespace = "com.xuwh.kotlinandroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.xuwh.kotlinandroid"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    kapt {
        correctErrorTypes = true
        generateStubs = true
    }

    hilt {
        enableAggregatingTask = false
    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Activity 中使用 viewModels()
    implementation(libs.androidx.activity.ktx)
    // Fragment 中使用 viewModels()
    implementation(libs.androidx.fragment.ktx)
    // Jetpack
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.databinding)

    // 协程
    implementation(libs.kotlinx.coroutines.core)

    // 网络
    implementation(libs.retrofit)
    implementation(libs.cookie)

    // Moshi 核心库
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)

    // Retrofit Moshi转换器
    implementation(libs.moshi.converter)
    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //刷新  加载更多
    implementation(libs.refresh.layout)      //核心必须依赖
    implementation(libs.refresh.header)    //谷歌刷新头
    implementation(libs.refresh.footer)   //经典加载

    // 图片加载
    implementation(libs.glide)

    // ARouter
    implementation(libs.arouter.api)
    kapt(libs.arouter.compiler)

    //flexbox
    implementation(libs.flexbox)
}