package com.example.android.politicalpreparedness.data.remote.dtos

import com.example.android.politicalpreparedness.domain.models.Channel

data class ChannelDTO (
    val type: String,
    val id: String
) {
    fun toModel(): Channel {
        return Channel(type, id)
    }
}