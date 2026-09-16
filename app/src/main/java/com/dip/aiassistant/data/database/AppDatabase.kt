package com.dip.aiassistant.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dip.aiassistant.data.dao.*
import com.dip.aiassistant.data.models.*

@Database(
    entities = [
        TodoItem::class,
        Note::class,
        Memory::class,
        ChatMessage::class,
        Reminder::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao
    abstract fun noteDao(): NoteDao
    abstract fun memoryDao(): MemoryDao
    abstract fun chatDao(): ChatDao
    abstract fun reminderDao(): ReminderDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: createDatabase(context).also { instance = it }
            }
        }

        private fun createDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "dip_ai_assistant_db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
