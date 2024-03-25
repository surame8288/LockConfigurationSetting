package com.test.lockconfiguration.ui.viewmodals

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.lifecycle.AndroidViewModel
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.test.lockconfiguration.api.HttpsClient
import com.test.lockconfiguration.concurrency.JobExecutor
import com.test.lockconfiguration.modal.AccessControlConfigurationModal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException

class AccessControlConfigurationViewMobal(application: Application) :
    AndroidViewModel(application) {
    private val TAG = "AccessControlConfigurationViewMobal"
    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    val sharedPreferences = EncryptedSharedPreferences.create(
        "preferences",
        masterKeyAlias,
        application.applicationContext,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    @Composable
    fun onClickHandler(isLoading: MutableState<Boolean>) {

        LaunchedEffect(isLoading.value) {
//            viewModel.sideEffects.collect { effect ->
//                // TODO handle the effect
//            }
            if (isLoading.value) {
                Log.d(TAG, "LaunchedEffectLaunchedEffect Ture")
                isLoading.value = false
            }
        }
    }

    fun getAccessControlConfigurationData() {
        JobExecutor.execute(::getAccessControlConfiguration)
    }

    @Composable
    fun getAccessControlConfigurationData_Composable(data: MutableState<AccessControlConfigurationModal>) {
        LaunchedEffect(data.value) {
            withContext(Dispatchers.IO) {
                val config = getAccessControlConfiguration()
                if (config != null) {
                    data.value = config
                }
            }
        }
    }

    private suspend fun getAccessControlConfiguration(): AccessControlConfigurationModal? {
        return try {
            val response = HttpsClient.client.getAccessControlConfiguration().execute()
            if (response != null && response.isSuccessful) {
                response.body()!!
            } else null
        } catch (se: SocketTimeoutException) {
            se.printStackTrace()
            null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}