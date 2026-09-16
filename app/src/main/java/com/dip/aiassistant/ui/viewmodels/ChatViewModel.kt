package com.dip.aiassistant.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dip.aiassistant.data.models.ChatMessage
import com.dip.aiassistant.data.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ChatUiState>(ChatUiState.Idle)
    val uiState = _uiState.asStateFlow()

    val allMessages: Flow<List<ChatMessage>> = chatRepository.getMessagesInOrder()
    val messageCount: Flow<Int> = chatRepository.getMessageCount()

    fun insertMessage(userMessage: String, aiResponse: String = "") {
        viewModelScope.launch {
            try {
                val userMsg = ChatMessage(
                    message = userMessage,
                    response = "",
                    isUser = true
                )
                val aiMsg = ChatMessage(
                    message = userMessage,
                    response = aiResponse,
                    isUser = false
                )
                chatRepository.insertMessage(userMsg)
                chatRepository.insertMessage(aiMsg)
                _uiState.value = ChatUiState.Success("Message saved")
            } catch (e: Exception) {
                _uiState.value = ChatUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun deleteMessage(message: ChatMessage) {
        viewModelScope.launch {
            try {
                chatRepository.deleteMessage(message)
                _uiState.value = ChatUiState.Success("Message deleted")
            } catch (e: Exception) {
                _uiState.value = ChatUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun deleteAllMessages() {
        viewModelScope.launch {
            try {
                chatRepository.deleteAllMessages()
                _uiState.value = ChatUiState.Success("Chat cleared")
            } catch (e: Exception) {
                _uiState.value = ChatUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun clearMessage() {
        _uiState.value = ChatUiState.Idle
    }
}

sealed class ChatUiState {
    object Idle : ChatUiState()
    object Loading : ChatUiState()
    data class Success(val message: String) : ChatUiState()
    data class Error(val message: String) : ChatUiState()
}
