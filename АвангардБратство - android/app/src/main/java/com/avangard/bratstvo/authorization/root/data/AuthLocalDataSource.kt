package com.avangard.bratstvo.authorization.root.data

import android.content.SharedPreferences

class AuthLocalDataSource(
    private val sharedPreferences: SharedPreferences
) {

    fun getAuthToken(): String? = sharedPreferences.getString(KEY_AUTH_TOKEN, null)

    fun getIsAuthorized(): Boolean = sharedPreferences.getBoolean(KEY_IS_AUTHORIZED, false)

    fun getRefreshToken(): String? = sharedPreferences.getString(KEY_AUTH_TOKEN, null)

    fun saveAuthToken(token: String) {
        saveToken(KEY_AUTH_TOKEN, token)
    }

    fun saveRefreshToken(token: String) {
        saveToken(KEY_REFRESH_TOKEN, token)
    }

    fun saveIsAuthorized() {
        sharedPreferences.edit().putBoolean(KEY_IS_AUTHORIZED, true).apply()
    }

    fun removeAuthToken() {
        removeToken(KEY_AUTH_TOKEN)
    }

    fun removeRefreshToken() {
        removeToken(KEY_REFRESH_TOKEN)
    }

    fun removeIsAuthorized() {
        removeToken(KEY_IS_AUTHORIZED)
    }

    private fun saveToken(key: String, token: String) {
        sharedPreferences.edit().putString(key, token).apply()
    }

    private fun removeToken(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    companion object {
        private const val KEY_AUTH_TOKEN = "auth_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
        private const val KEY_IS_AUTHORIZED = "is_authorized"
    }
}