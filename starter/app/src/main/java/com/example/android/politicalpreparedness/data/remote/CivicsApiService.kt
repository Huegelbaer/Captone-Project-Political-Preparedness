package com.example.android.politicalpreparedness.data.remote

import android.content.Context
import com.example.android.politicalpreparedness.data.remote.jsonadapter.DateAdapter
import com.example.android.politicalpreparedness.data.remote.jsonadapter.ElectionDivisionAdapter
import com.example.android.politicalpreparedness.data.remote.response.ElectionResponse
import com.example.android.politicalpreparedness.data.remote.response.RepresentativeResponse
import com.example.android.politicalpreparedness.data.remote.response.VoterInfoResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/**
 *  Documentation for the Google Civics API Service can be found at https://developers.google.com/civic-information/docs/v2
 */

interface CivicsApiService {
    @GET("elections")
    suspend fun getElections(): ElectionResponse

    @GET("voterinfo")
    suspend fun getVoterInfo(address: String, id: Int): VoterInfoResponse

    @GET("representatives")
    suspend fun getRepresentatives(address: String): RepresentativeResponse
}

class CivicsApi(context: Context) {

    private val moshi = Moshi.Builder()
        .add(ElectionDivisionAdapter())
        .add(DateAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(CivicsHttpClient.getClient(context))
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: CivicsApiService by lazy {
        retrofit.create(CivicsApiService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://www.googleapis.com/civicinfo/v2/"
    }
}