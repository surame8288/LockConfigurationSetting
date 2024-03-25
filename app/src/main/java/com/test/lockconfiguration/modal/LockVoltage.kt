package com.test.lockconfiguration.modal

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.gson.annotations.SerializedName
import com.test.lockconfiguration.modal.ConfigurationModal


data class LockVoltage (
  @SerializedName("values"  ) private var values  : ArrayList<String> = arrayListOf(),
  @SerializedName("default" ) private var default : String           = ""

) : ConfigurationModal<String> {
  val TAG = "LockVoltage"
  private var voltage = default

  override fun getValue(sharedPreferences: SharedPreferences): String {
    this.voltage = sharedPreferences.getString(TAG, default)!!
    return this.voltage
  }

  override fun getValues(): List<String> {
    return values
  }

  override fun getDefaultValue(): String = default.toString()

  @Composable
  override fun setValue(value: String, sharedPreferences: SharedPreferences) : MutableState<String> {
    sharedPreferences.edit().putString(TAG, value).apply()
    this.voltage = value
    var state = remember { mutableStateOf(this.voltage) }
    return state
  }

  override fun toString(): String {
    return this.voltage
  }
}