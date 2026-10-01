# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-dontusemixedcaseclassnames
-verbose

# Preserve line numbers for debugging stack traces
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Preserve all public and protected class members
-keep public class * {
    public protected *;
}

# Preserve all .class method names
-keepclassmembernames class * {
    java.lang.Class class$(java.lang.String);
    java.lang.Class class$(java.lang.String, boolean);
}

# Preserve all native method names and the names of their classes
-keepclasseswithmembernames class * {
    native <methods>;
}

# Preserve the special static initializer name and all accessors, if any
-keepclasseswithmembernames class * {
    static <clinit>();
}

# Preserve all .class method names and the names of their classes if they have native methods
-keepclasseswithmembernames class * {
    java.lang.Object clone();
}

# Preserve all .class method names and the names of their classes if they have finalize methods
-keepclasseswithmembernames class * {
    void finalize();
}

# Preserve all .class method names and the names of their classes if they override clone or finalize
-keepclasseswithmembernames class * {
    *** clone();
}

# Preserve all .class method names and the names of their classes if they have compareTo methods
-keepclasseswithmembernames class * {
    int compareTo(java.lang.Object);
}

# Preserve all classes that have custom compareTo methods
-keepclasseswithmembernames class * {
    int compareTo(...);
}

# Library-specific configurations
-keep class androidx.** { *; }
-keep interface androidx.** { *; }
