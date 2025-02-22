package com.example.android.politicalpreparedness.presentation.election

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.politicalpreparedness.databinding.FragmentElectionBinding
import com.example.android.politicalpreparedness.data.ElectionDataSource
import com.example.android.politicalpreparedness.presentation.election.adapter.ElectionClickListener
import com.example.android.politicalpreparedness.presentation.election.adapter.ElectionListAdapter
import org.koin.android.ext.android.inject

class ElectionsFragment: Fragment() {

    private lateinit var _viewModel: ElectionsViewModel
    private lateinit var _binding: FragmentElectionBinding
    private lateinit var _upcomingAdapter: ElectionListAdapter
    private lateinit var _savedAdapter: ElectionListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
    : View? {

        _binding = FragmentElectionBinding.inflate(inflater)

        val dataSource: ElectionDataSource by inject()
        _viewModel = ElectionsViewModel(dataSource)

        val upcomingClickListener = ElectionClickListener {
            _viewModel.onItemSelected(it)
        }
        _upcomingAdapter = ElectionListAdapter(upcomingClickListener)

        _binding.upcomingElectionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = _upcomingAdapter
        }

        val savedClickListener = ElectionClickListener {
            _viewModel.onItemSelected(it)
        }
        _savedAdapter = ElectionListAdapter(savedClickListener)

        _binding.savedElectionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = _savedAdapter
        }

        setupViewModelObservers()

        // TODO: Add binding values

        // TODO: Link elections to voter info

        // TODO: Populate recycler adapters

        return _binding.root
    }

    private fun setupViewModelObservers() {
        _viewModel.upcomingElections.observe(viewLifecycleOwner) {
            _upcomingAdapter.submitList(it)
        }

        _viewModel.savedElections.observe(viewLifecycleOwner) {
            _savedAdapter.submitList(it)
        }
    }

    // TODO: Refresh adapters when fragment loads
}