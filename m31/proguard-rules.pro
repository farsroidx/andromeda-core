-dontwarn java.lang.invoke.StringConcatFactory

# Keep all classes and interfaces in ir.farsroidx.m31 package
-keep class ir.farsroidx.m31.** { *; }

# Keep all fields and methods in ir.farsroidx.m31 package
-keepclassmembers class ir.farsroidx.m31.** { *; }

# If using reflection, keep the names of the fields in ir.farsroidx.m31 package
-keepclassmembers class ir.farsroidx.m31.** {
    <fields>;
}

# If using reflection, keep the names of the methods in ir.farsroidx.m31 package
-keepclassmembers class ir.farsroidx.m31.** {
    <methods>;
}

# Keep all extension functions in ir.farsroidx.m31 package
-keepclassmembers class ir.farsroidx.m31.** {
    public <methods>;
}

# Keep all public and protected fields and methods in ir.farsroidx.m31 package
-keepclassmembers class ir.farsroidx.m31.** {
    public protected <fields>;
    public protected <methods>;
}

# Keep all public and protected classes and interfaces in ir.farsroidx.m31 package
-keep class ir.farsroidx.m31.** {
    public protected *;
}