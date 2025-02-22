package com.example.android.politicalpreparedness.data.remote.dtos

import com.example.android.politicalpreparedness.domain.models.Office
import com.squareup.moshi.Json

data class OfficeDTO (
    val name: String,
    @Json(name="divisionId") val division: DivisionDTO,
    @Json(name="officialIndices") val officials: List<Int>
) {
    fun toModel(): Office {
        return Office(name, division.toModel(), officials)
    }
}