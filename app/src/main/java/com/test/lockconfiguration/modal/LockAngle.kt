package com.test.lockconfiguration.modal

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.gson.annotations.SerializedName


data class LockAngle<T : Double>(
    @SerializedName("range") var values: Range = Range(),
    @SerializedName("unit") var unit: String? = null,
    @SerializedName("default") var default: Double = 0.0,
    @SerializedName("common") var common: Boolean? = null
) : LockConfiguration<Double> {
    val TAG = "LockAngle"
    private var angle = default

    override fun getValue(sharedPreferences: SharedPreferences): Double {
        this.angle = sharedPreferences.getFloat(TAG, default.toFloat()).toString().toDouble()
        return this.angle
    }

    override fun getValues(): List<String> {
        return mutableListOf(values.min.toString(), values.max.toString())
    }

    override fun getDefaultValue(): T = default as T

    @Composable
    override fun <T> setValue(
        value: T,
        sharedPreferences: SharedPreferences
    ): MutableState<T> {
        Log.d("LockConfiguration", "LockAngle#setValue")
        this.angle = value as Double
        sharedPreferences.edit().putFloat(TAG, this.angle.toFloat()).apply()
        var state: MutableState<T> = remember { mutableStateOf(value) }
        return state
    }

    override fun toString(): String {
        return this.angle.toString()
    }

    override fun getClazz(): Class<Double> {
        return this.getClazz()
    }
}