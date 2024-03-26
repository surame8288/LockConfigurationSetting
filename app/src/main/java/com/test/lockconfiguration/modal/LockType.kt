package com.test.lockconfiguration.modal

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.gson.annotations.SerializedName


data class LockType<T : String>(
    @SerializedName("values") var values: ArrayList<String> = arrayListOf(),
    @SerializedName("default") var default: String = ""

) : LockConfiguration<String> {
    override val TAG = "LockType"
    private var type = default

    override fun getValue(sharedPreferences: SharedPreferences): String {
        this.type = sharedPreferences.getString(TAG, default)!!
        return this.type
    }

    override fun getValues(): List<String> {
        return values
    }

    override fun getDefaultValue(): T = default as T

    @Composable
    override fun <T> setValue(
        value: T,
        sharedPreferences: SharedPreferences
    ): MutableState<T> {
        this.type = value.toString()
        sharedPreferences.edit().putString(TAG, this.type).apply()
        var state: MutableState<T> = remember { mutableStateOf(value) }
        return state
    }

    override fun toString(): String {
        return this.type
    }

    override fun getClazz(): Class<String> {
        return String::class.java
    }
}