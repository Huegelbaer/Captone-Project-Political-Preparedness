package com.example.android.politicalpreparedness.domain.models

import java.util.Date

data class Election(
    val id: Int,
    val name: String,
    val electionDay: Date,
    val division: Division
)