package com.test.lockconfiguration.modal

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.gson.annotations.SerializedName


data class LockReleaseTime<T : Double>(
    @SerializedName("range") var values: Range = Range(),
    @SerializedName("unit") var unit: String? = null,
    @SerializedName("default") var default: Double = 0.0

) : LockConfiguration<Double> {
    override val TAG = "LockReleaseTime"
    private var time = default

    override fun getValue(sharedPreferences: SharedPreferences): Double {
        this.time = sharedPreferences.getFloat(TAG, default.toFloat()).toString().toDouble()

        return this.time
    }

    override fun getValues(): List<String> {
        return mutableListOf(values.min.toString(), values.max.toString())
    }

    override fun getDefaultValue(): Double = default

    @Composable
    override fun <T> setValue(
        value: T,
        sharedPreferences: SharedPreferences
    ): MutableState<T> {
        Log.d("LockConfiguration", "LockAngle#setValue")
        this.time = value as Double
        sharedPreferences.edit().putFloat(TAG, this.time.toFloat()).apply()
        var state: MutableState<T> = remember { mutableStateOf(value) }
        return state
    }

    override fun toString(): String {
        return this.time.toString()
    }

    override fun getClazz(): Class<Double> {
        return Double::class.java
    }
}