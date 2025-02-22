package com.example.android.politicalpreparedness.data.remote.dtos

import com.squareup.moshi.Json

data class ElectionOfficialDTO(
    val name: String,
    val title: String,
    @Json(name="officePhoneNumber") val phone: String,
    @Json(name="faxNumber") val fax: String,
    val emailAddress: String
)