package com.example.editingpost.Preferences

import android.content.Context

class Preferences (context : Context) {
    val PREFERANCE_NAME_SETTINGS = "settings"
    val PREFERANCE_TOKEN = "PREFERANCE_TOKEN"

    val preferences = context.getSharedPreferences(PREFERANCE_NAME_SETTINGS, Context.MODE_PRIVATE)

    fun getToken(): String? {
        return preferences.getString(PREFERANCE_TOKEN, "")
    }

    fun setToken(value: String) {
        val editor = preferences.edit()
        editor.putString(PREFERANCE_TOKEN, value)
        editor.apply()
    }
}