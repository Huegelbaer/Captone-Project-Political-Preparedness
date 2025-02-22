package com.example.android.politicalpreparedness.data

import com.example.android.politicalpreparedness.domain.models.Election
import com.example.android.politicalpreparedness.domain.models.Representative
import com.example.android.politicalpreparedness.domain.models.VoterInfo

interface ElectionDataSource {
    suspend fun getElections(): List<Election>
    suspend fun getVoterInfo(id: Int, address: String): VoterInfo
    suspend fun getRepresentatives(address: String): Representative
    suspend fun saveElection(election: Election)
    suspend fun getSavedElections(): List<Election>
    suspend fun getSavedElectionById(id: Int): Election?
    suspend fun removeAllSavedElections()
    suspend fun removeElectionById(id: Int)
}