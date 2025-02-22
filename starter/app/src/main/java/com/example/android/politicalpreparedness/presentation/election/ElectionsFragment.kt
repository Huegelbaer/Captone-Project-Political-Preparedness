package com.example.android.politicalpreparedness.presentation.election

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.politicalpreparedness.databinding.FragmentElectionBinding
import com.example.android.politicalpreparedness.domain.models.Election
import com.example.android.politicalpreparedness.presentation.election.adapter.ElectionClickListener
import com.example.android.politicalpreparedness.presentation.election.adapter.ElectionListAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ElectionsFragment : Fragment() {

    private val _viewModel: ElectionsViewModel by viewModel()
    private lateinit var _binding: FragmentElectionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentElectionBinding.inflate(inflater)
        _binding.viewModel = _viewModel
        _binding.lifecycleOwner = viewLifecycleOwner

        val upcomingClickListener = ElectionClickListener {
            _viewModel.onItemSelected(it)
        }

        _binding.upcomingElectionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ElectionListAdapter(upcomingClickListener)
        }

        val savedClickListener = ElectionClickListener {
            _viewModel.onItemSelected(it)
        }

        _binding.savedElectionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ElectionListAdapter(savedClickListener)
        }

        // TODO: Add binding values

        // TODO: Link elections to voter info

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                _viewModel.refresh()
            }
        }

        return _binding.root
    }

    // TODO: Refresh adapters when fragment loads
}