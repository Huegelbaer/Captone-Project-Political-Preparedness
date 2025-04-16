package com.example.android.politicalpreparedness.presentation.election

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.politicalpreparedness.data.ElectionDataSource
import com.example.android.politicalpreparedness.domain.models.Election
import com.example.android.politicalpreparedness.domain.models.VoterInfo
import kotlinx.coroutines.launch


import android.location.Address

class VoterInfoViewModel(private val repository: ElectionDataSource, val election: Election) : ViewModel() {

    //TODO: Add live data to hold voter info
    private val _voterInfo = MutableLiveData<VoterInfo>()
    val voterInfo: LiveData<VoterInfo>
        get() = _voterInfo

    //TODO: Add var and methods to populate voter info
    fun loadDetails(address: Address) {
        viewModelScope.launch {
            val exactAddress = address.getAddressLine(0)
            val response = repository.getVoterInfo(election.id, exactAddress)
            _voterInfo.value = response
        }

    //TODO: Add var and methods to support loading URLs
    }

    //TODO: Add var and methods to save and remove elections to local database
    //TODO: cont'd -- Populate initial state of save button to reflect proper action based on election saved status

    /**
     * Hint: The saved state can be accomplished in multiple ways. It is directly related to how elections are saved/removed from the database.
     */

}