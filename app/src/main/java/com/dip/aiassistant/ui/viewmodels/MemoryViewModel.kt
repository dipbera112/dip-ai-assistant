package com.dip.aiassistant.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dip.aiassistant.data.models.Memory
import com.dip.aiassistant.data.repository.MemoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoryViewModel @Inject constructor(
    private val memoryRepository: MemoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MemoryUiState>(MemoryUiState.Idle)
    val uiState = _uiState.asStateFlow()

    val allMemories: Flow<List<Memory>> = memoryRepository.getAllMemories()
    val memoryCount: Flow<Int> = memoryRepository.getMemoryCount()

    fun insertMemory(key: String, value: String, category: String = "general") {
        viewModelScope.launch {
            try {
                val memory = Memory(
                    key = key,
                    value = value,
                    category = category
                )
                memoryRepository.insertMemory(memory)
                _uiState.value = MemoryUiState.Success("Memory saved")
            } catch (e: Exception) {
                _uiState.value = MemoryUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun updateMemory(memory: Memory) {
        viewModelScope.launch {
            try {
                val updatedMemory = memory.copy(updatedAt = System.currentTimeMillis())
                memoryRepository.updateMemory(updatedMemory)
                _uiState.value = MemoryUiState.Success("Memory updated")
            } catch (e: Exception) {
                _uiState.value = MemoryUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun deleteMemory(memory: Memory) {
        viewModelScope.launch {
            try {
                memoryRepository.deleteMemory(memory)
                _uiState.value = MemoryUiState.Success("Memory deleted")
            } catch (e: Exception) {
                _uiState.value = MemoryUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun deleteAllMemories() {
        viewModelScope.launch {
            try {
                memoryRepository.deleteAllMemories()
                _uiState.value = MemoryUiState.Success("All memories cleared")
            } catch (e: Exception) {
                _uiState.value = MemoryUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun clearMessage() {
        _uiState.value = MemoryUiState.Idle
    }
}

sealed class MemoryUiState {
    object Idle : MemoryUiState()
    object Loading : MemoryUiState()
    data class Success(val message: String) : MemoryUiState()
    data class Error(val message: String) : MemoryUiState()
}
