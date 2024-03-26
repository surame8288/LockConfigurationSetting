package com.test.lockconfiguration.modal

import com.google.gson.annotations.SerializedName


data class AccessControlConfigurationModal(
    @SerializedName("lockVoltage") var lockVoltage: LockVoltage<String>? = null,
    @SerializedName("lockType") var lockType: LockType<String>? = null,
    @SerializedName("lockKick") var lockKick: LockKick<String>? = null,
    @SerializedName("lockRelease") var lockRelease: LockRelease<String>? = null,
    @SerializedName("lockReleaseTime") var lockReleaseTime: LockReleaseTime<Double>? = null,
    @SerializedName("lockAngle") var lockAngle: LockAngle<Double>? = null
)
