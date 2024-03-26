package com.test.lockconfiguration.modal

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.gson.annotations.SerializedName


data class LockKick<T : String>(
    @SerializedName("values") var values: ArrayList<String> = arrayListOf(),
    @SerializedName("default") var default: String = ""
) : LockConfiguration<String> {
    val TAG = "LockKick"
    private var kick = default

    override fun getValue(sharedPreferences: SharedPreferences): String {
        this.kick = sharedPreferences.getString(TAG, default)!!
        return this.kick
    }

    override fun getValues(): List<String> {
        return values
    }

    override fun getDefaultValue(): String = default

    @Composable
    override fun <T> setValue(
        value: T,
        sharedPreferences: SharedPreferences
    ): MutableState<T> {
        Log.d("LockConfiguration", "LockKick#setValue")
        sharedPreferences.edit().putString(TAG, value.toString()).apply()
        this.kick = value as String
        var state: MutableState<T> = remember { mutableStateOf(value) }
        return state
    }

    override fun toString(): String {
        return this.kick
    }

    override fun getClazz(): Class<String> {
        return this.getClazz()
    }
}