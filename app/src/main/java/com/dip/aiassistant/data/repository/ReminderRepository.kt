package com.dip.aiassistant.data.repository

import com.dip.aiassistant.data.dao.ReminderDao
import com.dip.aiassistant.data.models.Reminder
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReminderRepository @Inject constructor(
    private val reminderDao: ReminderDao
) {
    fun getAllReminders(): Flow<List<Reminder>> = reminderDao.getAllReminders()

    fun getActiveReminders(): Flow<List<Reminder>> = reminderDao.getActiveReminders()

    fun getActiveReminderCount(): Flow<Int> = reminderDao.getActiveReminderCount()

    suspend fun insertReminder(reminder: Reminder): Long = reminderDao.insertReminder(reminder)

    suspend fun updateReminder(reminder: Reminder) = reminderDao.updateReminder(reminder)

    suspend fun deleteReminder(reminder: Reminder) = reminderDao.deleteReminder(reminder)

    suspend fun getReminderById(id: Int): Reminder? = reminderDao.getReminderById(id)

    suspend fun updateReminderStatus(id: Int, isActive: Boolean) {
        reminderDao.updateReminderStatus(id, isActive)
    }

    suspend fun deleteAllReminders() = reminderDao.deleteAllReminders()
}
