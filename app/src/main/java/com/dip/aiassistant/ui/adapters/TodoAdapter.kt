package com.dip.aiassistant.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dip.aiassistant.data.models.TodoItem
import com.dip.aiassistant.databinding.ItemTodoBinding

class TodoAdapter(
    private val onToggle: (TodoItem) -> Unit,
    private val onDelete: (TodoItem) -> Unit
) : ListAdapter<TodoItem, TodoAdapter.TodoViewHolder>(
    object : DiffUtil.ItemCallback<TodoItem>() {
        override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean =
            oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder =
        TodoViewHolder(ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TodoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: TodoItem) {
            binding.tvTitle.text = todo.title
            binding.tvDescription.text = todo.description
            binding.cbCompleted.isChecked = todo.isCompleted

            binding.cbCompleted.setOnCheckedChangeListener { _, isChecked ->
                onToggle(todo.copy(isCompleted = isChecked))
            }

            binding.btnDelete.setOnClickListener {
                onDelete(todo)
            }
        }
    }
}
