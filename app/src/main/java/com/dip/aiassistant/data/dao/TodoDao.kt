package com.dip.aiassistant.data.dao

import androidx.room.*
import com.dip.aiassistant.data.models.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoItem): Long

    @Update
    suspend fun updateTodo(todo: TodoItem)

    @Delete
    suspend fun deleteTodo(todo: TodoItem)

    @Query("SELECT * FROM todo_items ORDER BY createdAt DESC")
    fun getAllTodos(): Flow<List<TodoItem>>

    @Query("SELECT * FROM todo_items WHERE id = :id")
    suspend fun getTodoById(id: Int): TodoItem?

    @Query("SELECT * FROM todo_items WHERE isCompleted = 0 ORDER BY priority DESC, dueDate ASC")
    fun getActiveTodos(): Flow<List<TodoItem>>

    @Query("SELECT * FROM todo_items WHERE isCompleted = 1 ORDER BY updatedAt DESC")
    fun getCompletedTodos(): Flow<List<TodoItem>>

    @Query("UPDATE todo_items SET isCompleted = :isCompleted, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateTodoStatus(id: Int, isCompleted: Boolean, updatedAt: Long = System.currentTimeMillis())

    @Query("DELETE FROM todo_items")
    suspend fun deleteAllTodos()

    @Query("SELECT COUNT(*) FROM todo_items WHERE isCompleted = 0")
    fun getActiveTodoCount(): Flow<Int>
}
