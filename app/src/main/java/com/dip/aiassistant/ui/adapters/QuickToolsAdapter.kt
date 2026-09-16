package com.dip.aiassistant.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dip.aiassistant.databinding.ItemQuickToolBinding

class QuickToolsAdapter(
    private val onToolClick: (Pair<String, String>) -> Unit
) : ListAdapter<Pair<String, String>, QuickToolsAdapter.ToolViewHolder>(
    object : DiffUtil.ItemCallback<Pair<String, String>>() {
        override fun areItemsTheSame(oldItem: Pair<String, String>, newItem: Pair<String, String>): Boolean =
            oldItem.second == newItem.second

        override fun areContentsTheSame(oldItem: Pair<String, String>, newItem: Pair<String, String>): Boolean =
            oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolViewHolder =
        ToolViewHolder(ItemQuickToolBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    override fun onBindViewHolder(holder: ToolViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ToolViewHolder(private val binding: ItemQuickToolBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tool: Pair<String, String>) {
            binding.tvToolName.text = tool.first
            binding.root.setOnClickListener {
                onToolClick(tool)
            }
        }
    }
}
