package com.emelyanov.rassvet.shared.domain.services.tokenstorage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import java.lang.reflect.Array.get

private const val AUTH_PREFERENCES_KEY = "auth_prefs_key"
private const val ACCESS_TOKEN_KEY = "access_token_key"
private const val REFRESH_TOKEN_KEY = "refresh_token_key"

class TokensStorage
constructor(
    appContext: Context
) : ITokensStorage{
    private var tokensStorage: SharedPreferences =
        appContext.getSharedPreferences(AUTH_PREFERENCES_KEY, MODE_PRIVATE)

    override var accessToken = ""
        get() = tokensStorage.getString(ACCESS_TOKEN_KEY, "") ?: ""
        set(value){
            field = value
            tokensStorage.edit().putString(ACCESS_TOKEN_KEY, value).apply()
        }

    override var refreshToken = ""
        get() = tokensStorage.getString(REFRESH_TOKEN_KEY, "") ?: ""
        set(value){
            field = value
            tokensStorage.edit().putString(REFRESH_TOKEN_KEY, value).apply()
        }


    override val areTokensExists: Boolean
            get() = tokensStorage.contains(REFRESH_TOKEN_KEY)
}