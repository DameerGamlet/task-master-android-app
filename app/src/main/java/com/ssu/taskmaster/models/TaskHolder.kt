package com.ssu.taskmaster.models

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ssu.taskmaster.R

class TaskHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val taskNameTextView: TextView = itemView.findViewById(R.id.task_name_textview)
    private val taskDescriptionTextView: TextView = itemView.findViewById(R.id._task_description)
    private val taskStatusTextView: TextView = itemView.findViewById(R.id.task_status)
    private val taskStartDateTextView: TextView =
        itemView.findViewById(R.id.task_start_date_textview)
    private val taskEndDateTextView: TextView = itemView.findViewById(R.id.task_end_date_textview)
    private val executeButton: ImageButton = itemView.findViewById(R.id.task_execute)
    private val editButton: ImageButton = itemView.findViewById(R.id.task_edit)
    private val deleteButton: ImageButton = itemView.findViewById(R.id.task_delete)

    fun bind(
        task: Task,
        onExecuteClickListener: TaskAdapter.OnExecuteClickListener,
        onInfoClickListener: TaskAdapter.OnInfoClickListener,
        onDeleteClickListener: TaskAdapter.OnDeleteClickListener,
    ) {
        taskNameTextView.text = task.name
        taskStatusTextView.text = if (task.isCompleted) "Выполнен" else "Не выполнен"
        taskDescriptionTextView.text = task.description
        taskStartDateTextView.text = task.startDate
        taskEndDateTextView.text = task.endDate

        executeButton.setOnClickListener {
            onExecuteClickListener.onExecuteClick(task)
        }

        editButton.setOnClickListener {
            onInfoClickListener.onInfoClick(task)
        }

        deleteButton.setOnClickListener {
            onDeleteClickListener.onDeleteClick(task)
        }
    }
}