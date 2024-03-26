package com.test.lockconfiguration.modal

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.gson.annotations.SerializedName


data class LockRelease<T : String>(
    @SerializedName("values") var values: ArrayList<String> = arrayListOf(),
    @SerializedName("default") var default: String = "",
    @SerializedName("common") var common: Boolean? = null

) : LockConfiguration<String> {
    val TAG = "LockRelease"
    private var release = default

    override fun getValue(sharedPreferences: SharedPreferences): String {
        this.release = sharedPreferences.getString(TAG, default)!!
        return this.release
    }

    override fun getValues(): List<String> {
        return values
    }

    override fun getDefaultValue(): String = default as T

    @Composable
    override fun <T> setValue(
        value: T,
        sharedPreferences: SharedPreferences
    ): MutableState<T> {
        this.release = value.toString()
        sharedPreferences.edit().putString(TAG, this.release).apply()
        var state: MutableState<T> = remember { mutableStateOf(value) }
        return state
    }

    override fun toString(): String {
        return this.release
    }

    override fun getClazz(): Class<String> {
        return String::class.java
    }
}