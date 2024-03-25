package com.test.lockconfiguration.modal

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.gson.annotations.SerializedName


data class LockAngle (
  @SerializedName("range"   ) var values   : Range   = Range(),
  @SerializedName("unit"    ) var unit    : String?  = null,
  @SerializedName("default" ) var default : Double     = 0.0,
  @SerializedName("common"  ) var common  : Boolean? = null
) : ConfigurationModal<Double> {
  val TAG = "LockAngle"
  private var angle = default

  override fun getValue(sharedPreferences: SharedPreferences): Double? {
    this.angle = sharedPreferences.getFloat(TAG, default.toFloat())!!.toString().toDouble()

    return this.angle
  }

  override fun getValues(): List<String> {
    return mutableListOf(values.min.toString(), values.max.toString())
  }

  override fun getDefaultValue(): Double = default

  @Composable
  override fun setValue(value: Double, sharedPreferences: SharedPreferences) : MutableState<Double> {
    Log.d("ConfigurationModal", "LockAngle#setValue")
    sharedPreferences.edit().putFloat(TAG, value.toFloat()).apply()
    this.angle = value
    var state = remember { mutableStateOf(this.angle) }
    return state
  }

  override fun toString(): String {
    return  this.angle.toString()
  }
}