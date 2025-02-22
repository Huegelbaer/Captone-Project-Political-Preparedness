package com.example.android.politicalpreparedness.data.remote.response

import com.example.android.politicalpreparedness.data.remote.dtos.OfficeDTO
import com.example.android.politicalpreparedness.data.remote.dtos.OfficialDTO
import com.example.android.politicalpreparedness.domain.models.Representative

data class RepresentativeResponse(
    val offices: List<OfficeDTO>,
    val officials: List<OfficialDTO>
) {
    fun toModel(): Representative {
        return Representative(offices.map { it.toModel() }, officials.map { it.toModel() })
    }
}