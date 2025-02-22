package com.example.android.politicalpreparedness.data.repository

import com.example.android.politicalpreparedness.data.ElectionDataSource
import com.example.android.politicalpreparedness.data.local.ElectionDao
import com.example.android.politicalpreparedness.data.local.entities.ElectionEntity
import com.example.android.politicalpreparedness.data.remote.CivicsApi
import com.example.android.politicalpreparedness.data.remote.CivicsApiService
import com.example.android.politicalpreparedness.domain.models.Election
import com.example.android.politicalpreparedness.domain.models.Representative
import com.example.android.politicalpreparedness.domain.models.VoterInfo

class ElectionRepository(
    private val electionDao: ElectionDao,
    private val apiService: CivicsApiService
) : ElectionDataSource {

    override suspend fun getElections(): List<Election> =  apiService.getElections().elections.map { it.toModel() }

    override suspend fun getVoterInfo(
        id: Int,
        address: String
    ): VoterInfo {
        return CivicsApi.retrofitService.getVoterInfo(address, id).toModel()
    }

    override suspend fun getRepresentatives(
        address: String
    ): Representative {
        return CivicsApi.retrofitService.getRepresentatives(address).toModel()
    }

    override suspend fun saveElection(election: Election) =
        electionDao.insert(ElectionEntity.fromModel(election))


    override suspend fun getSavedElections(): List<Election> =
        electionDao.getAll().map { it.toModel() }


    override suspend fun getSavedElectionById(id: Int): Election? =
        electionDao.getById(id)?.toModel()


    override suspend fun removeAllSavedElections() =
        electionDao.clear()


    override suspend fun removeElectionById(id: Int) =
        electionDao.deleteById(id)

}