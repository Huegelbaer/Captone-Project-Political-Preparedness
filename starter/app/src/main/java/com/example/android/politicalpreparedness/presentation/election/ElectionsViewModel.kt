package com.example.android.politicalpreparedness.presentation.election

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.politicalpreparedness.data.ElectionDataSource
import com.example.android.politicalpreparedness.domain.models.Election
import kotlinx.coroutines.launch
import timber.log.Timber

class ElectionsViewModel(
    private val electionRepository: ElectionDataSource
): ViewModel() {

    private var _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var _upcomingElections = MutableLiveData<List<Election>>()
    val upcomingElections: LiveData<List<Election>>
        get() = _upcomingElections

    private var _savedElections = MutableLiveData<List<Election>>()
    val savedElections: LiveData<List<Election>>
        get() = _savedElections

    //TODO: Create val and functions to populate live data for upcoming elections from the API and saved elections from local database

    //TODO: Create functions to navigate to saved or upcoming election voter info

    init {
        getElections()
    }

    private fun getElections() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _upcomingElections.value = electionRepository.getElections()
                _savedElections.value = electionRepository.getSavedElections()
            } catch (e: Exception) {
                e.localizedMessage?.let { Timber.tag("network error").e(it) }
              //  _errorMessage.value = R.string.error_message
            }
            _isLoading.postValue(false)
        }
    }

    fun refreshLoads() {
        getElections()
    }

    fun onItemSelected(item: Election) {

    }
}