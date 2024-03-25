package com.test.lockconfiguration.modal

import com.google.gson.annotations.SerializedName


data class Range(

    @SerializedName("min") var min: Double = 0.0,
    @SerializedName("max") var max: Double = 0.0

)