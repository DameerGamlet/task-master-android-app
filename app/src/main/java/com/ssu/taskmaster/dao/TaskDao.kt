package com.ssu.taskmaster.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ssu.taskmaster.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    fun insertTask(task: Task)

    @Query("SELECT * FROM tasks")
    fun getAllTask(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE is_completed=false")
    fun getActiveTasks(): Flow<List<Task>>
}