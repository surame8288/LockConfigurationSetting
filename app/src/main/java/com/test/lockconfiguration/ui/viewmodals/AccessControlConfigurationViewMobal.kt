package com.test.lockconfiguration.ui.viewmodals

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.lifecycle.AndroidViewModel
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.test.lockconfiguration.modal.AccessControlConfigurationModal
import com.test.lockconfiguration.api.HttpsClient
import com.test.lockconfiguration.concurrency.JobExecutor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccessControlConfigurationViewMobal(application: Application) : AndroidViewModel(application) {
    private val TAG = "AccessControlConfigurationViewMobal"

    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    // Initialize/open an instance of EncryptedSharedPreferences on below line.
    val sharedPreferences = EncryptedSharedPreferences.create(
        // passing a file name to share a preferences
        "preferences",
        masterKeyAlias,
        application.applicationContext,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
//    lateinit var dataAccessControlConfiguration : MutableState<AccessControlConfigurationModal>


//    fun isDataModalInitialised(): Boolean = ::dataAccessControlConfiguration.isInitialized
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

    fun getAccessControlConfigurationData(){
        JobExecutor.execute(::getAccessControlConfiguration)

    }

    @Composable
    fun getAccessControlConfigurationData_Composable(data : MutableState<AccessControlConfigurationModal>){

        LaunchedEffect(data.value){
            data.value = withContext(Dispatchers.IO){
                getAccessControlConfiguration()!!
            }


        }


    }



    private suspend fun getAccessControlConfiguration() : AccessControlConfigurationModal? {
        val response = HttpsClient.client.getAccessControlConfiguration().execute()
        return  if(response.isSuccessful){
            response.body()!!
          //  dataAccessControlConfiguration = mutableStateOf(response.body()!!)

//            dataAccessControlConfiguration.value.lockRelease.let {
//               // it.setValue(value = "Good Please help me...")
//            }

           // dataAccessControlConfiguration.value  = response.body()!!


        }else null
    }


}