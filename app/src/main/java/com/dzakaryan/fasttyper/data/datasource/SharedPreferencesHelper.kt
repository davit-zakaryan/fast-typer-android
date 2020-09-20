package com.dzakaryan.fasttyper.data.datasource

import android.content.SharedPreferences
import com.dzakaryan.fasttyper.domain.datasource.UserLocalDataSource
import com.dzakaryan.fasttyper.domain.model.User
import com.google.gson.Gson

class SharedPreferencesHelper(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson,
) : UserLocalDataSource {

    //region Override open methods
    override suspend fun getUser(): User {
        return gson.fromJson(sharedPreferences.getString(PREF_CACHE_USER_MODEL, ""),
            User::class.java)
    }

    override suspend fun setUser(user: User) {
        sharedPreferences.edit().putString(PREF_CACHE_USER_MODEL, gson.toJson(user)).apply()
    }
    //endregion

    //region Companion object
    companion object {
        const val PREF_CACHE_USER_MODEL = "pref.CACHE_USER_MODEL"
    }
    //endregion
}