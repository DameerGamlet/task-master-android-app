package com.ssu.taskmaster.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ssu.taskmaster.dao.TaskDao
import com.ssu.taskmaster.models.Task

@Database(
    entities = [
        Task::class
    ],
    version = 1
)
abstract class TaskHelperDb : RoomDatabase() {
    abstract fun getDao(): TaskDao

    companion object {
        fun getConnect(context: Context): TaskHelperDb {
            return Room.databaseBuilder(
                context.applicationContext,
                TaskHelperDb::class.java,
                "taskmaster.db"
            ).build();
        }
    }
}