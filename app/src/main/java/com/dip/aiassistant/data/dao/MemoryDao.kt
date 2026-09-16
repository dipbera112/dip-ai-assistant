package com.dip.aiassistant.data.dao

import androidx.room.*
import com.dip.aiassistant.data.models.Memory
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMemory(memory: Memory): Long

    @Update
    suspend fun updateMemory(memory: Memory)

    @Delete
    suspend fun deleteMemory(memory: Memory)

    @Query("SELECT * FROM memories ORDER BY createdAt DESC")
    fun getAllMemories(): Flow<List<Memory>>

    @Query("SELECT * FROM memories WHERE category = :category ORDER BY createdAt DESC")
    fun getMemoriesByCategory(category: String): Flow<List<Memory>>

    @Query("SELECT * FROM memories WHERE key = :key LIMIT 1")
    suspend fun getMemoryByKey(key: String): Memory?

    @Query("SELECT * FROM memories WHERE id = :id")
    suspend fun getMemoryById(id: Int): Memory?

    @Query("DELETE FROM memories")
    suspend fun deleteAllMemories()

    @Query("SELECT COUNT(*) FROM memories")
    fun getMemoryCount(): Flow<Int>
}
