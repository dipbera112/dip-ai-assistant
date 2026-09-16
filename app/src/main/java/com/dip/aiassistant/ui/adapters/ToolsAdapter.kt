package com.dip.aiassistant.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dip.aiassistant.databinding.ItemToolBinding

class ToolsAdapter(
    private val onToolClick: (Triple<String, String, String>) -> Unit
) : ListAdapter<Triple<String, String, String>, ToolsAdapter.ToolViewHolder>(
    object : DiffUtil.ItemCallback<Triple<String, String, String>>() {
        override fun areItemsTheSame(oldItem: Triple<String, String, String>, newItem: Triple<String, String, String>): Boolean =
            oldItem.third == newItem.third

        override fun areContentsTheSame(oldItem: Triple<String, String, String>, newItem: Triple<String, String, String>): Boolean =
            oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolViewHolder =
        ToolViewHolder(ItemToolBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    override fun onBindViewHolder(holder: ToolViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ToolViewHolder(private val binding: ItemToolBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tool: Triple<String, String, String>) {
            binding.tvIcon.text = tool.first
            binding.tvToolName.text = tool.second
            binding.root.setOnClickListener {
                onToolClick(tool)
            }
        }
    }
}
