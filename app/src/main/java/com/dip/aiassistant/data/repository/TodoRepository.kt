package com.dip.aiassistant.data.repository

import com.dip.aiassistant.data.dao.TodoDao
import com.dip.aiassistant.data.models.TodoItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoDao: TodoDao
) {
    fun getAllTodos(): Flow<List<TodoItem>> = todoDao.getAllTodos()

    fun getActiveTodos(): Flow<List<TodoItem>> = todoDao.getActiveTodos()

    fun getCompletedTodos(): Flow<List<TodoItem>> = todoDao.getCompletedTodos()

    fun getActiveTodoCount(): Flow<Int> = todoDao.getActiveTodoCount()

    suspend fun insertTodo(todo: TodoItem): Long = todoDao.insertTodo(todo)

    suspend fun updateTodo(todo: TodoItem) = todoDao.updateTodo(todo)

    suspend fun deleteTodo(todo: TodoItem) = todoDao.deleteTodo(todo)

    suspend fun getTodoById(id: Int): TodoItem? = todoDao.getTodoById(id)

    suspend fun updateTodoStatus(id: Int, isCompleted: Boolean) {
        todoDao.updateTodoStatus(id, isCompleted)
    }

    suspend fun deleteAllTodos() = todoDao.deleteAllTodos()
}
