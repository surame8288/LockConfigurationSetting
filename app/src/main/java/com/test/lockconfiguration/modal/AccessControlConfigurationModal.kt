package com.test.lockconfiguration.modal

import com.google.gson.annotations.SerializedName


data class AccessControlConfigurationModal (

  @SerializedName("lockVoltage"     ) var lockVoltage     : LockVoltage?      = null,
  @SerializedName("lockType"        ) var lockType        : LockType?         = null,
  @SerializedName("lockKick"        ) var lockKick        : LockKick?         = null,
  @SerializedName("lockRelease"     ) var lockRelease     : LockRelease?      = null,
  @SerializedName("lockReleaseTime" ) var lockReleaseTime : LockReleaseTime?  = null,
  @SerializedName("lockAngle"       ) var lockAngle       : LockAngle?        = null



)
