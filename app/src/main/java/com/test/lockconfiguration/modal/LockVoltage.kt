package com.test.lockconfiguration.modal

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.gson.annotations.SerializedName


data class LockVoltage<T : String>(
    @SerializedName("values") private var values: ArrayList<String> = arrayListOf(),
    @SerializedName("default") private var default: String = ""

) : LockConfiguration<String> {
    override val TAG = "LockVoltage"
    private var voltage = default

    override fun getValue(sharedPreferences: SharedPreferences): String {
        this.voltage = sharedPreferences.getString(TAG, default)!!
        return this.voltage
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
        Log.d("LockConfiguration", "LockAngle#setValue")
        this.voltage = value as String
        sharedPreferences.edit().putString(TAG, this.voltage).apply()
        var state: MutableState<T> = remember { mutableStateOf(value) }
        return state
    }

    override fun toString(): String {
        return this.voltage
    }

    override fun getClazz(): Class<String> {
        return String::class.java
    }
}