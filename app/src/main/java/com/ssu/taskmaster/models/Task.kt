package com.ssu.taskmaster.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    var name: String,

    var description: String,

    @ColumnInfo(name = "start_date")
    var startDate: String,

    @ColumnInfo(name = "end_date")
    var endDate: String,

    @ColumnInfo(name = "is_notify")
    var isNotify: Boolean = false,

    @ColumnInfo(name = "notify_time")
    var notifyTime: String,

    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean = false


) {
    override fun toString(): String {
        return "Task(id=$id, name='$name', description='$description', startDate='$startDate', endDate='$endDate', isNotify=$isNotify, notifyTime='$notifyTime', isCompleted=$isCompleted)"
    }
}