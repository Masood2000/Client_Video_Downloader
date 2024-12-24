# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature

# OkHttp
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }
-keepattributes Signature
-keepattributes *Annotation*

# Gson
-keep class com.google.gson.** { *; }
-keepattributes Signature
-keepattributes *Annotation*



-keep class com.google.api.** { *; }
-keep class com.google.gson.** { *; }
-keep class com.google.android.gms.** { *; }
-keep class retrofit2.** { *; }
-keepattributes *Annotation*
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-keep class com.example.apiproject.data.** { *; }
-keep interface com.example.apiproject.data.** { *; }
-keep interface com.example.apiproject.data.gag.** { *; }
-keep class com.example.apiproject.data.gag.** { *; }
-keep class com.example.apiproject.data.gag.model.** { *; }
-keep class com.example.apiproject.data.models.gag.** { *; }
-keep class com.example.apiproject.data.models.** { *; }
-keep class com.example.apiproject.data.PostRepository
-keep class com.example.apiproject.domain.events.** { *; }
-keep interface com.example.apiproject.domain.events.** { *; }
-keep class com.example.apiproject.domain.extractor.** { *; }
-keep interface com.example.apiproject.domain.extractor.** { *; }
-keep interface com.example.apiproject.domain.extractor.** { *; }
-keep class com.example.apiproject.domain.** { *; }
-keep interface com.example.apiproject.domain.** { *; }

-keep class com.example.apiproject.data.api.** { *; }
-keep interface com.example.apiproject.data.api.** { *; }



-dontwarn io.realm.**