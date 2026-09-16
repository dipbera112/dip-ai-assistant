package com.dip.aiassistant.data.repository

import com.dip.aiassistant.data.dao.NoteDao
import com.dip.aiassistant.data.models.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {
    fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes()

    fun getPinnedNotes(): Flow<List<Note>> = noteDao.getPinnedNotes()

    fun getNoteCount(): Flow<Int> = noteDao.getNoteCount()

    suspend fun insertNote(note: Note): Long = noteDao.insertNote(note)

    suspend fun updateNote(note: Note) = noteDao.updateNote(note)

    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

    suspend fun getNoteById(id: Int): Note? = noteDao.getNoteById(id)

    suspend fun updateNotePinStatus(id: Int, isPinned: Boolean) {
        noteDao.updateNotePinStatus(id, isPinned)
    }

    suspend fun deleteAllNotes() = noteDao.deleteAllNotes()
}
