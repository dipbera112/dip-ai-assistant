package com.dip.aiassistant.di

import android.content.Context
import androidx.room.Room
import com.dip.aiassistant.data.database.AppDatabase
import com.dip.aiassistant.data.dao.*
import com.dip.aiassistant.data.repository.*
import com.dip.aiassistant.utils.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "dip_ai_assistant_db"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideTodoDao(database: AppDatabase): TodoDao = database.todoDao()

    @Singleton
    @Provides
    fun provideNoteDao(database: AppDatabase): NoteDao = database.noteDao()

    @Singleton
    @Provides
    fun provideMemoryDao(database: AppDatabase): MemoryDao = database.memoryDao()

    @Singleton
    @Provides
    fun provideChatDao(database: AppDatabase): ChatDao = database.chatDao()

    @Singleton
    @Provides
    fun provideReminderDao(database: AppDatabase): ReminderDao = database.reminderDao()

    @Singleton
    @Provides
    fun provideTodoRepository(todoDao: TodoDao): TodoRepository =
        TodoRepository(todoDao)

    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository =
        NoteRepository(noteDao)

    @Singleton
    @Provides
    fun provideMemoryRepository(memoryDao: MemoryDao): MemoryRepository =
        MemoryRepository(memoryDao)

    @Singleton
    @Provides
    fun provideChatRepository(chatDao: ChatDao): ChatRepository =
        ChatRepository(chatDao)

    @Singleton
    @Provides
    fun provideReminderRepository(reminderDao: ReminderDao): ReminderRepository =
        ReminderRepository(reminderDao)

    @Singleton
    @Provides
    fun providePreferenceManager(
        @ApplicationContext context: Context
    ): PreferenceManager = PreferenceManager(context)
}
