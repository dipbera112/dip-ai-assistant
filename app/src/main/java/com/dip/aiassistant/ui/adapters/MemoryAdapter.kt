package com.dip.aiassistant.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dip.aiassistant.data.models.Memory
import com.dip.aiassistant.databinding.ItemMemoryBinding

class MemoryAdapter(
    private val onDelete: (Memory) -> Unit
) : ListAdapter<Memory, MemoryAdapter.MemoryViewHolder>(
    object : DiffUtil.ItemCallback<Memory>() {
        override fun areItemsTheSame(oldItem: Memory, newItem: Memory): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Memory, newItem: Memory): Boolean =
            oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryViewHolder =
        MemoryViewHolder(ItemMemoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    override fun onBindViewHolder(holder: MemoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MemoryViewHolder(private val binding: ItemMemoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(memory: Memory) {
            binding.tvKey.text = memory.key
            binding.tvValue.text = memory.value
            binding.tvCategory.text = memory.category

            binding.btnDelete.setOnClickListener {
                onDelete(memory)
            }
        }
    }
}
