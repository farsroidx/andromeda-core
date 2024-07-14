# Ignore warnings about java.lang.invoke.StringConcatFactory
-dontwarn java.lang.invoke.StringConcatFactory

# Keep all classes and interfaces in ir.farsroidx.m31 package
-keep class ir.farsroidx.m31.** { *; }

# Keep all fields, methods, and variables in ir.farsroidx.m31 package
-keepclassmembers class ir.farsroidx.m31.** { *; }

# Keep all extension functions in ir.farsroidx.m31 package
-keepclassmembers class ir.farsroidx.m31.** {
    <methods>;
    <fields>;
}

# Preserve Kotlin metadata
-keep class kotlin.Metadata { *; }
-keep class kotlin.jvm.internal.* { *; }