package com.example.android.politicalpreparedness.data.remote.response

import com.example.android.politicalpreparedness.data.remote.dtos.ElectionDTO
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ElectionResponse(
    val kind: String,
    val elections: List<ElectionDTO>
)