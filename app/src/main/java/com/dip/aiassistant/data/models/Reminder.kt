package com.dip.aiassistant.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class Reminder(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String = "",
    val reminderTime: Long,
    val isActive: Boolean = true,
    val repeatType: String = "once", // once, daily, weekly, monthly
    val createdAt: Long = System.currentTimeMillis()
)
