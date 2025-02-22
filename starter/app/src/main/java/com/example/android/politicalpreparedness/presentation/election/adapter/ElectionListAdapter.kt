package com.example.android.politicalpreparedness.presentation.election.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.politicalpreparedness.databinding.FragmentElectionListItemBinding
import com.example.android.politicalpreparedness.domain.models.Election

class ElectionListAdapter(private val clickListener: ElectionClickListener)
    : ListAdapter<Election, ElectionViewHolder>(ElectionDiffCallback()) {

    override fun onBindViewHolder(holder: ElectionViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectionViewHolder {
        return ElectionViewHolder.from(parent)
    }
}

class ElectionViewHolder(private val binding: FragmentElectionListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Election, clickListener: ElectionClickListener) {
        binding.election = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ElectionViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = FragmentElectionListItemBinding.inflate(layoutInflater, parent, false)
            return ElectionViewHolder(binding)
        }
    }
}

class ElectionDiffCallback: DiffUtil.ItemCallback<Election>() {
    override fun areItemsTheSame(oldItem: Election, newItem: Election): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Election, newItem: Election): Boolean {
        return  oldItem.name == newItem.name && oldItem.electionDay == newItem.electionDay
    }

}

// TODO: Create ElectionListener
class ElectionClickListener(val onClickListener: (election: Election) -> Unit) {
    fun onClick(election: Election) = onClickListener(election)
}
