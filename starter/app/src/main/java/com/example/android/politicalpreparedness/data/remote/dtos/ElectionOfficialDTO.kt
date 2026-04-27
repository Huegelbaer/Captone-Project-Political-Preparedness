package com.example.android.politicalpreparedness.data.remote.dtos

import com.example.android.politicalpreparedness.domain.models.ElectionOfficial
import com.squareup.moshi.Json

data class ElectionOfficialDTO(
    val name: String,
    val title: String,
    @param:Json(name="officePhoneNumber") val phone: String,
    @param:Json(name="faxNumber") val fax: String,
    val emailAddress: String
) {
    fun toModel(): ElectionOfficial {
        return ElectionOfficial(name, title, phone, fax, emailAddress)
    }
}