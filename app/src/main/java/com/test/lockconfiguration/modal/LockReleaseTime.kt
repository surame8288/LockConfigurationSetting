package com.test.lockconfiguration.modal

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.gson.annotations.SerializedName


data class LockReleaseTime (
  @SerializedName("range"   ) var values   : Range  = Range(),
  @SerializedName("unit"    ) var unit    : String? = null,
  @SerializedName("default" ) var default : Double = 0.0

) : ConfigurationModal<Double> {
  val TAG = "LockReleaseTime"
  private var time = default

  override fun getValue(sharedPreferences: SharedPreferences): Double {
    this.time = sharedPreferences.getFloat(TAG, default.toFloat())!!.toString().toDouble()

    return this.time
  }

  override fun getValues(): List<String> {
    return mutableListOf(values.min.toString(), values.max.toString())
  }

  override fun getDefaultValue(): Double = default

  @Composable
  override fun setValue(value: Double, sharedPreferences: SharedPreferences) : MutableState<Double> {
    sharedPreferences.edit().putFloat(TAG, value.toFloat()).apply()
    this.time = value
    var state = remember { mutableStateOf(this.time) }
    return state
  }

  override fun toString(): String {
    return this.time.toString()
  }
}