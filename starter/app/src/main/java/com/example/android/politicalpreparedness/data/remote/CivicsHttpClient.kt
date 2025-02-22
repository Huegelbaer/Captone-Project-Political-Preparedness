package com.example.android.politicalpreparedness.data.remote

import android.content.Context
import com.example.android.politicalpreparedness.BuildConfig
import okhttp3.OkHttpClient

class CivicsHttpClient : OkHttpClient() {

    companion object {


        fun getClient(context: Context): OkHttpClient {
            return Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val url = original
                        .url()
                        .newBuilder()
                        .addQueryParameter("productionDataOnly", "true")
                        .addQueryParameter("key", BuildConfig.API_KEY)
                        .build()
                    val request = original
                        .newBuilder()
                        .url(url)
                        .build()
                    chain.proceed(request)
                }
                .addInterceptor(NetworkConnectionInterceptor(context))
                .build()
        }

    }

}