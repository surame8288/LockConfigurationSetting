package com.test.lockconfiguration.modal

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.gson.annotations.SerializedName


data class LockRelease(
    @SerializedName("values") var values: ArrayList<String> = arrayListOf(),
    @SerializedName("default") var default: String = "",
    @SerializedName("common") var common: Boolean? = null

) : ConfigurationModal<String> {
    val TAG = "LockRelease"
    private var release = default

    override fun getValue(sharedPreferences: SharedPreferences): String {
        this.release = sharedPreferences.getString(TAG, default)!!
        return this.release
    }

    override fun getValues(): List<String> {
        return values
    }

    override fun getDefaultValue(): String = default

    @Composable
    override fun setValue(
        value: String,
        sharedPreferences: SharedPreferences
    ): MutableState<String> {
        sharedPreferences.edit().putString(TAG, value).apply()
        this.release = value
        var state = remember { mutableStateOf(this.release) }
        return state
    }

    override fun toString(): String {
        return this.release
    }
}