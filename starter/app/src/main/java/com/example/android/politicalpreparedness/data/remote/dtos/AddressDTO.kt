package com.example.android.politicalpreparedness.data.remote.dtos

import com.example.android.politicalpreparedness.domain.models.Address

data class AddressDTO (
        val line1: String,
        val line2: String? = null,
        val city: String,
        val state: String,
        val zip: String
) {
    fun toFormattedString(): String {
        var output = line1.plus("\n")
        if (!line2.isNullOrEmpty()) output = output.plus(line2).plus("\n")
        output = output.plus("$city, $state $zip")
        return output
    }

    fun toModel(): Address {
        return Address(line1, line2, city, state, zip)
    }
}