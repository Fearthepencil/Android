package com.example.levi9application.viewModels

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private var sharedPreferences: SharedPreferences
) : ViewModel() {

    private val editor = sharedPreferences.edit()

    fun getString(key: String, defaultValue: String?): String {
        return sharedPreferences.getString(key, defaultValue) ?: ""
    }

    fun putString(key: String, value: String) {
        editor.apply{
            putString(key, value)
            apply()
        }
    }

    fun clear(){
        editor.clear()
        editor.apply()
    }
}
