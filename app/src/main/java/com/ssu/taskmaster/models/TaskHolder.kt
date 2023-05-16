package com.ssu.taskmaster.models

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ssu.taskmaster.R

class TaskHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView: TextView = itemView.findViewById(R.id.task_name_textview)
    private val descriptionTextView: TextView = itemView.findViewById(R.id.task_date_textview)

    fun bind(task: Task) {
        titleTextView.text = task.name
        descriptionTextView.text = task.description
    }
}
