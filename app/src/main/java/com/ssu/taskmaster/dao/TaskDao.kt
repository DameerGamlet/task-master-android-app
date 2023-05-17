package com.ssu.taskmaster.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ssu.taskmaster.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    fun insertTask(task: Task)

    @Query("SELECT * FROM tasks WHERE name=:name")
    fun getTaskByName(name: String): Task?

    @Query("SELECT * FROM tasks")
    fun getAllTask(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE is_completed=:isCompleted")
    fun getTasksByStatus(isCompleted: Boolean): Flow<List<Task>>

    @Query("UPDATE tasks SET is_completed=:isCompleted WHERE id=:taskId")
    fun updateTaskCompletionStatus(taskId: Long?, isCompleted: Boolean)
    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("DELETE FROM tasks WHERE is_completed=true")
    suspend fun deleteCompletedTasks()

    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()
}