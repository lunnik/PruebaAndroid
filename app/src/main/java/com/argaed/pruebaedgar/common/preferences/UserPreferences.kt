package com.argaed.pruebaedgar.common.preferences

import android.content.Context

fun Context.saveUsername( username: String) {
    val prefs = this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    prefs.edit().putString("username", username).apply()
}

fun Context.getUsername(): String? {
    val prefs = this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    return prefs.getString("username", null)
}

fun Context.clearUsername() {
    val prefs = this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    prefs.edit().remove("username").apply()
}