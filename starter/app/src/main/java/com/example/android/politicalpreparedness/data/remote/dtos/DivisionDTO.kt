package com.example.android.politicalpreparedness.data.remote.dtos

import com.example.android.politicalpreparedness.domain.models.Division
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DivisionDTO(
        val id: String,
        val country: String,
        val state: String
) {
    fun toModel(): Division {
        return Division(id, country, state)
    }
}