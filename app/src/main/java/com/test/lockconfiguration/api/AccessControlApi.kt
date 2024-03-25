package com.test.lockconfiguration.api

import com.test.lockconfiguration.modal.AccessControlConfigurationModal
import retrofit2.Call
import retrofit2.http.GET

interface AccessControlApi {
    @GET("1b37a82f-bfa7-4eb9-aafb-f02fc32c43d4")
            /*suspend*/ fun getAccessControlConfiguration(): Call<AccessControlConfigurationModal>
}