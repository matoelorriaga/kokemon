# kokémon

This project shows how to implement MVP in Android. Is similar to [this](https://github.com/matoelorriaga/pokemon) project, but using Kotlin.

```groovy
// Android Support Library
compile "com.android.support:appcompat-v7:25.3.1"
compile "com.android.support:design:25.3.1"
compile "com.android.support:cardview-v7:25.3.1"
compile "com.android.support:recyclerview-v7:25.3.1"
compile "com.android.support:support-v4:25.3.1"
compile "com.android.support:support-annotations:25.3.1"

// Kotlin
compile "org.jetbrains.kotlin:kotlin-stdlib:1.1.1"
compile "org.jetbrains.kotlin:kotlin-reflect:1.1.1"

// Anko
compile "org.jetbrains.anko:anko-sdk15:0.10.0-beta-2"
compile "org.jetbrains.anko:anko-appcompat-v7:0.10.0-beta-2"
compile "org.jetbrains.anko:anko-design:0.10.0-beta-2"
compile "org.jetbrains.anko:anko-cardview-v7:0.10.0-beta-2"
compile "org.jetbrains.anko:anko-recyclerview-v7:0.10.0-beta-2"
compile "org.jetbrains.anko:anko-support-v4:0.10.0-beta-2"

// Dagger 2
compile "com.google.dagger:dagger:2.10"
kapt "com.google.dagger:dagger-compiler:2.10"

// Gson
compile "com.google.code.gson:gson:2.8.0"

// OkHttp
compile "com.squareup.okhttp3:okhttp:3.6.0"
compile "com.squareup.okhttp3:logging-interceptor:3.6.0"

// Retrofit
compile "com.squareup.retrofit2:retrofit:2.2.0"
compile "com.squareup.retrofit2:converter-gson:2.2.0"
compile "com.squareup.retrofit2:adapter-rxjava:2.2.0"

// Picasso
compile "com.squareup.picasso:picasso:2.5.2"

// RxJava & RxAndroid
compile "io.reactivex:rxjava:1.2.9"
compile "io.reactivex:rxandroid:1.2.1"

// JUnit
testCompile "junit:junit:4.12"

// PowerMock
testCompile "org.powermock:powermock-module-junit4:1.7.0RC2"
testCompile "org.powermock:powermock-api-mockito2:1.7.0RC2"

// Mockito-Kotlin
testCompile "com.nhaarman:mockito-kotlin:1.4.0"

// Hamcrest
testCompile "org.hamcrest:hamcrest-junit:2.0.0.0"

// Kotlin Test Support
testCompile "org.jetbrains.kotlin:kotlin-test:1.1.1"
testCompile "org.jetbrains.kotlin:kotlin-test-junit:1.1.1"

// Espresso
androidTestCompile "com.android.support.test.espresso:espresso-core:2.2.2"

// RESTMock
androidTestCompile "com.github.andrzejchm.RESTMock:android:0.2.0"
```
