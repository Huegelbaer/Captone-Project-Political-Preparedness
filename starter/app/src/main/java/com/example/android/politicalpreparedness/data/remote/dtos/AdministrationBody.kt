package com.example.android.politicalpreparedness.data.remote.dtos

import com.example.android.politicalpreparedness.domain.models.Administration
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdministrationBody(
    val name: String? = null,
    val electionInfoUrl: String? = null,
    val votingLocationFinderUrl: String? = null,
    val ballotInfoUrl: String? = null,
    val correspondenceAddress: AddressDTO? = null
) {
    fun toModel(): Administration {
        return Administration(
            name,
            electionInfoUrl,
            votingLocationFinderUrl,
            ballotInfoUrl,
            correspondenceAddress?.toModel()
        )
    }
}