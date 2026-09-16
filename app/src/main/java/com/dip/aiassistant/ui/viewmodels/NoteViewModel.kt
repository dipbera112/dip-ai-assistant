package com.dip.aiassistant.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dip.aiassistant.data.models.Note
import com.dip.aiassistant.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<NoteUiState>(NoteUiState.Idle)
    val uiState = _uiState.asStateFlow()

    val allNotes: Flow<List<Note>> = noteRepository.getAllNotes()
    val pinnedNotes: Flow<List<Note>> = noteRepository.getPinnedNotes()
    val noteCount: Flow<Int> = noteRepository.getNoteCount()

    fun insertNote(title: String, content: String, color: String = "#1A1F3A") {
        viewModelScope.launch {
            try {
                val note = Note(
                    title = title,
                    content = content,
                    color = color
                )
                noteRepository.insertNote(note)
                _uiState.value = NoteUiState.Success("Note added successfully")
            } catch (e: Exception) {
                _uiState.value = NoteUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            try {
                val updatedNote = note.copy(updatedAt = System.currentTimeMillis())
                noteRepository.updateNote(updatedNote)
                _uiState.value = NoteUiState.Success("Note updated")
            } catch (e: Exception) {
                _uiState.value = NoteUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            try {
                noteRepository.deleteNote(note)
                _uiState.value = NoteUiState.Success("Note deleted")
            } catch (e: Exception) {
                _uiState.value = NoteUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun updateNotePinStatus(id: Int, isPinned: Boolean) {
        viewModelScope.launch {
            try {
                noteRepository.updateNotePinStatus(id, isPinned)
            } catch (e: Exception) {
                _uiState.value = NoteUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch {
            try {
                noteRepository.deleteAllNotes()
                _uiState.value = NoteUiState.Success("All notes cleared")
            } catch (e: Exception) {
                _uiState.value = NoteUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun clearMessage() {
        _uiState.value = NoteUiState.Idle
    }
}

sealed class NoteUiState {
    object Idle : NoteUiState()
    object Loading : NoteUiState()
    data class Success(val message: String) : NoteUiState()
    data class Error(val message: String) : NoteUiState()
}
