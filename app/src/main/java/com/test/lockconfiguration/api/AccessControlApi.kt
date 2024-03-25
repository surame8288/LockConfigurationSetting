package com.test.lockconfiguration.api

import com.test.lockconfiguration.modal.AccessControlConfigurationModal
import retrofit2.Call
import retrofit2.http.GET

interface AccessControlApi {
    @GET("d5f5d613-474b-49c4-a7b0-7730e8f8f486")
            /*suspend*/ fun getAccessControlConfiguration(): Call<AccessControlConfigurationModal>
}