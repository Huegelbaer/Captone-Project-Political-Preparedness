package com.example.android.politicalpreparedness.domain.models

data class VoterInfo(
    val election: Election,
    val pollingLocations: String?,
    val contests: String?,
    val state: List<State>?,
    val officials: List<ElectionOfficial>?
) {
 val administration: Administration?
     get() = state?.firstOrNull()?.electionAdministration
}