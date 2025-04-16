package com.example.android.politicalpreparedness.domain.models

import java.util.Date
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Election(
    val id: Int,
    val name: String,
    val electionDay: Date,
    val division: Division
): Parcelable