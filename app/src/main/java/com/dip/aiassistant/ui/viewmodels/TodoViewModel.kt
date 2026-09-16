package com.dip.aiassistant.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dip.aiassistant.data.models.TodoItem
import com.dip.aiassistant.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<TodoUiState>(TodoUiState.Loading)
    val uiState = _uiState.asStateFlow()

    val allTodos: Flow<List<TodoItem>> = todoRepository.getAllTodos()
    val activeTodos: Flow<List<TodoItem>> = todoRepository.getActiveTodos()
    val completedTodos: Flow<List<TodoItem>> = todoRepository.getCompletedTodos()
    val activeTodoCount: Flow<Int> = todoRepository.getActiveTodoCount()

    fun insertTodo(title: String, description: String = "", priority: Int = 0, dueDate: Long? = null) {
        viewModelScope.launch {
            try {
                val todo = TodoItem(
                    title = title,
                    description = description,
                    priority = priority,
                    dueDate = dueDate
                )
                todoRepository.insertTodo(todo)
                _uiState.value = TodoUiState.Success("Todo added successfully")
            } catch (e: Exception) {
                _uiState.value = TodoUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun updateTodo(todo: TodoItem) {
        viewModelScope.launch {
            try {
                val updatedTodo = todo.copy(updatedAt = System.currentTimeMillis())
                todoRepository.updateTodo(updatedTodo)
                _uiState.value = TodoUiState.Success("Todo updated")
            } catch (e: Exception) {
                _uiState.value = TodoUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun deleteTodo(todo: TodoItem) {
        viewModelScope.launch {
            try {
                todoRepository.deleteTodo(todo)
                _uiState.value = TodoUiState.Success("Todo deleted")
            } catch (e: Exception) {
                _uiState.value = TodoUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun toggleTodoStatus(id: Int, isCompleted: Boolean) {
        viewModelScope.launch {
            try {
                todoRepository.updateTodoStatus(id, isCompleted)
            } catch (e: Exception) {
                _uiState.value = TodoUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun deleteAllTodos() {
        viewModelScope.launch {
            try {
                todoRepository.deleteAllTodos()
                _uiState.value = TodoUiState.Success("All todos cleared")
            } catch (e: Exception) {
                _uiState.value = TodoUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun clearMessage() {
        _uiState.value = TodoUiState.Idle
    }
}

sealed class TodoUiState {
    object Idle : TodoUiState()
    object Loading : TodoUiState()
    data class Success(val message: String) : TodoUiState()
    data class Error(val message: String) : TodoUiState()
}
