package com.ssu.taskmaster.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.ssu.taskmaster.db.TaskHelperDb
import com.ssu.taskmaster.models.Task

class TaskService(private val taskDb: TaskHelperDb) {

    fun getAllTasks(): LiveData<List<Task>> {
        return taskDb.getDao().getAllTask().asLiveData()
    }

    fun getActiveTasks(): LiveData<List<Task>> {
        return taskDb.getDao().getTasksByStatus(false).asLiveData()
    }

    fun getCompletedTasks(): LiveData<List<Task>> {
        return taskDb.getDao().getTasksByStatus(true).asLiveData()
    }

    fun updateTaskCompletionStatus(task: Task, isCompleted: Boolean) {
        Thread {
            taskDb.getDao().updateTaskCompletionStatus(task.id, isCompleted)
        }.start()
    }

    fun deleteTask(task: Task) {
        Thread {
            taskDb.getDao().deleteTask(task)
        }.start()
    }
}