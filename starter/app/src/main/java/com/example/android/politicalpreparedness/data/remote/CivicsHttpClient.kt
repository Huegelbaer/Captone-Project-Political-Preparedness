package com.example.android.politicalpreparedness.data.remote

import android.content.Context
import okhttp3.OkHttpClient

class CivicsHttpClient : OkHttpClient() {

    companion object {

        const val API_KEY = "" //TODO: Place your API Key Here

        fun getClient(context: Context): OkHttpClient {
            return Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val url = original
                        .url()
                        .newBuilder()
                        .addQueryParameter("productionDataOnly", "true")
                        .addQueryParameter("key", API_KEY)
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