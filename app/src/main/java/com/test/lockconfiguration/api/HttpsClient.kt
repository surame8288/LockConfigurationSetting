package com.test.lockconfiguration.api

import android.util.Base64
import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object HttpsClient {
    private val TAG = "HttpsClient"
    val client = geHTTPClient().create(AccessControlApi::class.java)

    private fun geHTTPClient(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
//            if (BuildConfig.DEBUG) {
//                this.setLevel(HttpLoggingInterceptor.Level.BODY)
//            } else {
//                this.setLevel(HttpLoggingInterceptor.Level.BASIC)
//            }
        }

        val customHeaderInterceptor = Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.addHeader(
                "Accept",
                "*/*"
            )
            val request = requestBuilder.build()
            Log.d(TAG," Url : ${ request.url.toUrl().path }")
            val response = chain.proceed(request)
            Log.d(TAG, "StatusCode : ${response.code}")
            response
        }
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(customHeaderInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
//            .baseUrl("https://delegation-service2-1-0.azurewebsites.net")
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().disableHtmlEscaping().create()
                )
            )
            .client(client)
            .build()
    }
}