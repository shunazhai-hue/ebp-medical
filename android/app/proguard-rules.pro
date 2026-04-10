# EBP Medical App ProGuard Rules
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

# WebView JavaScript Interface
-keepclassmembers class com.ebpmedical.app.AndroidBridge {
    @android.webkit.JavascriptInterface <methods>;
}

# Keep all public classes
-keep public class com.ebpmedical.app.** { *; }
