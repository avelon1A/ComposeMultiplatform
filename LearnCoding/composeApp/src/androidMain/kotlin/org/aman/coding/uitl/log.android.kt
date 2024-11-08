package org.aman.coding.uitl

// Logger.kt
// Logger.android.kt
actual object Logger {
    actual fun d(tag: String, message: String) {
        android.util.Log.d(tag, message)
    }

    actual fun e(tag: String, message: String, throwable: Throwable?) {
        android.util.Log.e(tag, message, throwable)
    }
}
