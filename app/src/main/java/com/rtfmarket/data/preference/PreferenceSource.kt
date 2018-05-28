package com.rtfmarket.data.preference

import android.content.Context
import android.content.SharedPreferences
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class PreferenceSource
@Inject constructor(private val appContext: Context) {
    companion object {
        const val APP_PREFERENCES = "APP_PREFERENCES"
        const val AUTH_TOKEN = "AUTH_TOKEN"
        const val CURRENT_USER_ID = "CURRENT_USER_ID"
    }


    private val preferenceSubject = BehaviorSubject.create<String>()
    private val sharedPreferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        preferenceSubject.onNext(key)
    }
    private var registrated = false

    private val preferences by lazy {
        appContext.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    }

    fun setAuthToken(authToken: String) {
        preferences.edit()
                .putString(AUTH_TOKEN, authToken)
                .apply()
    }

    fun setCurrentUserId(userId: String) {
        preferences.edit()
                .putString(CURRENT_USER_ID, userId)
                .apply()
    }


    fun getAuthToken(): String {
        return preferences.getString(AUTH_TOKEN, "")
    }

    fun getCurrentUserId(): String {
        return preferences.getString(CURRENT_USER_ID, "")
    }

    fun getCurrentUserSubscription(): Flowable<String> {
        return getPreferenceChangeFlowable()
                .filter { key -> key == CURRENT_USER_ID }
                .map { key -> preferences.getString(key, "") }
    }


    private fun getPreferenceChangeFlowable(): Flowable<String> {
        if (!registrated) {
            preferences.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener)
            preferenceSubject.onNext(CURRENT_USER_ID)
            registrated = true
        }
        return preferenceSubject.toFlowable(BackpressureStrategy.ERROR)
    }
}