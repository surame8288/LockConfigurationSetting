package com.test.lockconfiguration.modal

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.gson.annotations.SerializedName


data class LockKick (
  @SerializedName("values"  ) var values  : ArrayList<String> = arrayListOf(),
  @SerializedName("default" ) var default : String           = ""
) : ConfigurationModal<String>{
  val TAG = "LockKick"
  private var kick  = default

  override fun getValue(sharedPreferences: SharedPreferences): String {
    this.kick = sharedPreferences.getString(TAG, default)!!
    return this.kick
  }

  override fun getValues(): List<String> {
    return values
  }

  override fun getDefaultValue(): String = default

  @Composable
  override fun setValue(value: String, sharedPreferences: SharedPreferences) : MutableState<String> {
    Log.d("ConfigurationModal", "LockKick#setValue")
    sharedPreferences.edit().putString(TAG, value).apply()
    this.kick = value
    var state = remember { mutableStateOf(this.kick) }
    return state
  }

  override fun toString(): String {
    return this.kick
  }
}