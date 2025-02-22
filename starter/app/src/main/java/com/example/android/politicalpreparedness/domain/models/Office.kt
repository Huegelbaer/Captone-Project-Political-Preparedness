package com.example.android.politicalpreparedness.domain.models

data class Office (
    val name: String,
    val division:Division,
    val officials: List<Int>
) {
/*
    fun getRepresentatives(officials: List<Official>): List<Representative> {
        return this.officials.map { index ->
            Representative(officials[index], this)
        }
    }
 */
}
