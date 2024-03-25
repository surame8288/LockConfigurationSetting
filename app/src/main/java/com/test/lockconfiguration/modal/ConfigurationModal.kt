package com.test.lockconfiguration.modal

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

interface ConfigurationModal<T> {
    fun getValue(sharedPreferences: SharedPreferences) : T?
    fun getValues() : List<String>
    fun getDefaultValue() : T
    @Composable fun setValue(value : T, sharedPreferences: SharedPreferences) : MutableState<T>

}