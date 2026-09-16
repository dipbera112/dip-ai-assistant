package com.dip.aiassistant.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dip.aiassistant.data.models.Note
import com.dip.aiassistant.databinding.ItemNoteBinding

class NoteAdapter(
    private val onDelete: (Note) -> Unit,
    private val onPin: (Note) -> Unit
) : ListAdapter<Note, NoteAdapter.NoteViewHolder>(
    object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean =
            oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.tvTitle.text = note.title
            binding.tvContent.text = note.content
            binding.root.setCardBackgroundColor(android.graphics.Color.parseColor(note.color))

            binding.btnDelete.setOnClickListener {
                onDelete(note)
            }

            binding.btnPin.setOnClickListener {
                onPin(note)
            }
        }
    }
}
