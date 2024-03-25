package com.test.lockconfiguration.modal

import com.google.gson.annotations.SerializedName
import kotlin.reflect.KClass


data class AccessControlConfigurationModal (

  @SerializedName("lockVoltage"     ) var lockVoltage     : LockVoltage     = LockVoltage(),
  @SerializedName("lockType"        ) var lockType        : LockType        = LockType(),
  @SerializedName("lockKick"        ) var lockKick        : LockKick        = LockKick(),
  @SerializedName("lockRelease"     ) var lockRelease     : LockRelease     = LockRelease(),
  @SerializedName("lockReleaseTime" ) var lockReleaseTime : LockReleaseTime = LockReleaseTime(),
  @SerializedName("lockAngle"       ) var lockAngle       : LockAngle       = LockAngle()



)
