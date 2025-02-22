package com.example.android.politicalpreparedness.data.remote.dtos

import android.os.Parcelable
import com.example.android.politicalpreparedness.domain.models.Division
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class DivisionDTO(
        val id: String,
        val country: String,
        val state: String
) : Parcelable {
    fun toModel(): Division {
        return Division(id, country, state)
    }
}