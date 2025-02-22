package com.example.android.politicalpreparedness.domain.models

data class VoterInfo(
    val election: Election,
    val state: List<State>? = null,
)