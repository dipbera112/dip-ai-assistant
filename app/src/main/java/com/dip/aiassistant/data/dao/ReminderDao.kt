package com.dip.aiassistant.data.dao

import androidx.room.*
import com.dip.aiassistant.data.models.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: Reminder): Long

    @Update
    suspend fun updateReminder(reminder: Reminder)

    @Delete
    suspend fun deleteReminder(reminder: Reminder)

    @Query("SELECT * FROM reminders ORDER BY reminderTime ASC")
    fun getAllReminders(): Flow<List<Reminder>>

    @Query("SELECT * FROM reminders WHERE isActive = 1 ORDER BY reminderTime ASC")
    fun getActiveReminders(): Flow<List<Reminder>>

    @Query("SELECT * FROM reminders WHERE id = :id")
    suspend fun getReminderById(id: Int): Reminder?

    @Query("UPDATE reminders SET isActive = :isActive WHERE id = :id")
    suspend fun updateReminderStatus(id: Int, isActive: Boolean)

    @Query("DELETE FROM reminders")
    suspend fun deleteAllReminders()

    @Query("SELECT COUNT(*) FROM reminders WHERE isActive = 1")
    fun getActiveReminderCount(): Flow<Int>
}
