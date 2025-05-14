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
import com.example.android.politicalpreparedness.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VoterInfoViewModel(private val repository: ElectionDataSource, val election: Election) : ViewModel() {

    //TODO: Add live data to hold voter info
    private val _voterInfo = MutableLiveData<VoterInfo>()
    val voterInfo: LiveData<VoterInfo>
        get() = _voterInfo

    private val _isElectionSaved = MutableLiveData<Boolean>()
    val isElectionSaved:  LiveData<Boolean>
        get() = _isElectionSaved

    private val _message = MutableLiveData<String>()
    val message:  LiveData<String>
        get() = _message

    init {
        loadSavedState()
    }

    private fun loadSavedState() {
        viewModelScope.launch(Dispatchers.IO) {
            val savedElection = repository.getSavedElectionById(election.id)
            withContext(Dispatchers.Main) {
                _isElectionSaved.value = savedElection != null
            }
        }
    }

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

    suspend fun saveElection() {
        viewModelScope.launch(Dispatchers.IO) {
            val succeeded = repository.saveElection(election)
            withContext(Dispatchers.Main) {
                if (succeeded) {
                    _isElectionSaved.value = true
                } else {
                    _message.value = R.string.save_election_failed
                }
            }
        }
    }

    suspend fun deleteElection() {
        viewModelScope.launch(Dispatchers.IO) {
            val succeeded =  repository.removeElectionById(election.id)
            withContext(Dispatchers.Main) {
                if (succeeded) {
                    _isElectionSaved.value = false
                } else {
                    _message.value = R.string.remove_election_failed
                }
            }
        }
    }

    /**
     * Hint: The saved state can be accomplished in multiple ways. It is directly related to how elections are saved/removed from the database.
     */

}