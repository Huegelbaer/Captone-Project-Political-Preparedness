package com.example.android.politicalpreparedness.data.remote.response

import com.example.android.politicalpreparedness.data.remote.dtos.ElectionDTO
import com.example.android.politicalpreparedness.data.remote.dtos.ElectionOfficialDTO
import com.example.android.politicalpreparedness.data.remote.dtos.StateDTO
import com.example.android.politicalpreparedness.domain.models.VoterInfo
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class VoterInfoResponse(
    val election: ElectionDTO,
    val pollingLocations: String? = null, //TODO: Future Use
    val contests: String? = null, //TODO: Future Use
    val state: List<StateDTO>? = null,
    val electionElectionOfficials: List<ElectionOfficialDTO>? = null
) {
    fun toModel(): VoterInfo {
        return VoterInfo(election.toModel(), state?.map { it.toModel() })
    }
}