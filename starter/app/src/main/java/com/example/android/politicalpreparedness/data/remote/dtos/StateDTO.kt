package com.example.android.politicalpreparedness.data.remote.dtos

import com.example.android.politicalpreparedness.domain.models.State

data class StateDTO(
    val name: String,
    val electionAdministrationBody: AdministrationBody
) {
    fun toModel(): State {
        return State(name, electionAdministrationBody.toModel())
    }
}