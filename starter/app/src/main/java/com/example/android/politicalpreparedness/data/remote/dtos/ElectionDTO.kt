package com.example.android.politicalpreparedness.data.remote.dtos

import com.example.android.politicalpreparedness.domain.models.Election
import com.squareup.moshi.*
import java.util.*

data class ElectionDTO(
        val id: Int,
        val name: String,
        val electionDay: Date,
        @Json(name="ocdDivisionId") val division: DivisionDTO
) {
    fun toModel(): Election {
        return Election(id, name, electionDay, division.toModel())
    }
}