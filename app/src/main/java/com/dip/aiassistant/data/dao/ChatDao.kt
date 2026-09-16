package com.dip.aiassistant.data.dao

import androidx.room.*
import com.dip.aiassistant.data.models.ChatMessage
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: ChatMessage): Long

    @Delete
    suspend fun deleteMessage(message: ChatMessage)

    @Query("SELECT * FROM chat_messages ORDER BY timestamp DESC")
    fun getAllMessages(): Flow<List<ChatMessage>>

    @Query("SELECT * FROM chat_messages ORDER BY timestamp ASC")
    fun getMessagesInOrder(): Flow<List<ChatMessage>>

    @Query("SELECT * FROM chat_messages WHERE id = :id")
    suspend fun getMessageById(id: Int): ChatMessage?

    @Query("DELETE FROM chat_messages")
    suspend fun deleteAllMessages()

    @Query("SELECT COUNT(*) FROM chat_messages")
    fun getMessageCount(): Flow<Int>

    @Query("SELECT * FROM chat_messages ORDER BY timestamp DESC LIMIT :limit")
    fun getLastMessages(limit: Int): Flow<List<ChatMessage>>
}
