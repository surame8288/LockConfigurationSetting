package com.test.lockconfiguration.modal

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.gson.annotations.SerializedName


data class LockType(
    @SerializedName("values") var values: ArrayList<String> = arrayListOf(),
    @SerializedName("default") var default: String = ""

) : ConfigurationModal<String> {
    val TAG = "LockType"
    private var type = default

    override fun getValue(sharedPreferences: SharedPreferences): String {
        this.type = sharedPreferences.getString(TAG, default)!!
        return this.type
    }

    override fun getValues(): List<String> {
        return values
    }

    override fun getDefaultValue(): String = default.toString()

    @Composable
    override fun setValue(
        value: String,
        sharedPreferences: SharedPreferences
    ): MutableState<String> {
        Log.d("ConfigurationModal", "LockType#setValue")
        sharedPreferences.edit().putString(TAG, value).apply()
        this.type = value
        var state = remember { mutableStateOf(this.type) }
        return state
    }

    override fun toString(): String {
        return this.type
    }
}