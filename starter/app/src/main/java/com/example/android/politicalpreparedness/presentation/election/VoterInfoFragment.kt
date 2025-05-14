package com.example.android.politicalpreparedness.presentation.election

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.android.politicalpreparedness.R
import com.example.android.politicalpreparedness.databinding.FragmentVoterInfoBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class VoterInfoFragment : Fragment() {

    private val _params: VoterInfoFragmentArgs by navArgs()
    private val _viewModel: VoterInfoViewModel by viewModel { parametersOf(_params.election) }
    private lateinit var _binding: FragmentVoterInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVoterInfoBinding.inflate(inflater)
        _binding.viewModel = _viewModel
        _binding.lifecycleOwner = viewLifecycleOwner

        // TODO: Add binding values

        // TODO: Populate voter info -- hide views without provided data.

        /**
        Hint: You will need to ensure proper data is provided from previous fragment.
        */

        // TODO: Handle loading of URLs

        _viewModel.isElectionSaved.observe(viewLifecycleOwner) { isSaved ->
            if (isSaved) {
                _binding.saveButton.apply {
                    setImageResource(android.R.drawable.ic_menu_delete)
                    contentDescription = resources.getText(R.string.remove_election)
                    setOnClickListener { lifecycleScope.launch { _viewModel.deleteElection() } }
                }
            } else {
                _binding.saveButton.apply {
                    setImageResource(android.R.drawable.ic_menu_save)
                    contentDescription = resources.getText(R.string.save_election)
                    setOnClickListener { lifecycleScope.launch { _viewModel.saveElection() } }
                }
            }
        }

        _viewModel.message.observe(viewLifecycleOwner) { message ->
            showSnackbar(message)
        }

        return _binding.root
    }

    private fun showSnackbar(message: String) {
        Snackbar
            .make(requireView(), message, Snackbar.LENGTH_LONG)
            .show()
    }

    // TODO: Create method to load URL intents
}