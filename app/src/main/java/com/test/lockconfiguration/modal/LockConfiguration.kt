package com.test.lockconfiguration.modal

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState


interface LockConfiguration<T> {
    fun getValue(sharedPreferences: SharedPreferences): T?
    fun getValues(): List<String>
    fun getDefaultValue(): T

    @Composable
    fun <T> setValue(value: T, sharedPreferences: SharedPreferences): MutableState<T>

    fun getClazz(): Class<T>

}