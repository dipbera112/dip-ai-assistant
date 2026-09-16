package com.dip.aiassistant.data.repository

import com.dip.aiassistant.data.dao.MemoryDao
import com.dip.aiassistant.data.models.Memory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemoryRepository @Inject constructor(
    private val memoryDao: MemoryDao
) {
    fun getAllMemories(): Flow<List<Memory>> = memoryDao.getAllMemories()

    fun getMemoriesByCategory(category: String): Flow<List<Memory>> =
        memoryDao.getMemoriesByCategory(category)

    fun getMemoryCount(): Flow<Int> = memoryDao.getMemoryCount()

    suspend fun insertMemory(memory: Memory): Long = memoryDao.insertMemory(memory)

    suspend fun updateMemory(memory: Memory) = memoryDao.updateMemory(memory)

    suspend fun deleteMemory(memory: Memory) = memoryDao.deleteMemory(memory)

    suspend fun getMemoryById(id: Int): Memory? = memoryDao.getMemoryById(id)

    suspend fun getMemoryByKey(key: String): Memory? = memoryDao.getMemoryByKey(key)

    suspend fun deleteAllMemories() = memoryDao.deleteAllMemories()
}
