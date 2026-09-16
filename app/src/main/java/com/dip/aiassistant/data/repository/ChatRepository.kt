package com.dip.aiassistant.data.repository

import com.dip.aiassistant.data.dao.ChatDao
import com.dip.aiassistant.data.models.ChatMessage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val chatDao: ChatDao
) {
    fun getAllMessages(): Flow<List<ChatMessage>> = chatDao.getAllMessages()

    fun getMessagesInOrder(): Flow<List<ChatMessage>> = chatDao.getMessagesInOrder()

    fun getMessageCount(): Flow<Int> = chatDao.getMessageCount()

    suspend fun insertMessage(message: ChatMessage): Long = chatDao.insertMessage(message)

    suspend fun deleteMessage(message: ChatMessage) = chatDao.deleteMessage(message)

    suspend fun getMessageById(id: Int): ChatMessage? = chatDao.getMessageById(id)

    suspend fun deleteAllMessages() = chatDao.deleteAllMessages()

    fun getLastMessages(limit: Int): Flow<List<ChatMessage>> = chatDao.getLastMessages(limit)
}
