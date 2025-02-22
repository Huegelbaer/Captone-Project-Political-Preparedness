package com.example.android.politicalpreparedness.data.remote.dtos

import com.example.android.politicalpreparedness.domain.models.Official

data class OfficialDTO(
    val name: String,
    val address: List<AddressDTO>? = null,
    val party: String? = null,
    val phones: List<String>? = null,
    val urls: List<String>? = null,
    val photoUrl: String? = null,
    val channels: List<ChannelDTO>? = null
) {
    fun toModel(): Official {
        return Official(
            name,
            address?.map { it.toModel() },
            party,
            phones,
            urls,
            photoUrl,
            channels?.map { it.toModel() })
    }
}