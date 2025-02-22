package com.example.android.politicalpreparedness.data.local.entities

import android.os.Parcelable
import com.example.android.politicalpreparedness.domain.models.Division
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DivisionEntity(
    val id: String,
    val country: String,
    val state: String
) : Parcelable {
    fun toModel(): Division {
        return Division(id, country, state)
    }

    companion object {
        fun fromModel(division: Division): DivisionEntity {
            return DivisionEntity(division.id, division.country, division.state)
        }
    }
}