package com.ssu.taskmaster.models

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssu.taskmaster.R

class TaskAdapter(
    private val context: Context,
    private val onExecuteClickListener: OnExecuteClickListener,
    private val onEditClickListener: OnEditClickListener,
    private val onDeleteClickListener: OnDeleteClickListener
) : RecyclerView.Adapter<TaskHolder>() {

    private var tasks: List<Task> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.task_items, parent, false)
        return TaskHolder(view)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val task = tasks[position]
        holder.bind(
            task,
            onExecuteClickListener,
            onEditClickListener,
            onDeleteClickListener
        )
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTasks(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    interface OnExecuteClickListener {
        fun onExecuteClick(task: Task)
    }

    interface OnEditClickListener {
        fun onEditClick(task: Task)
    }

    interface OnDeleteClickListener {
        fun onDeleteClick(task: Task)
    }
}


//class TaskAdapter(var tasks: List<TaskItem>) :
//    RecyclerView.Adapter<TaskHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
//        val view = LayoutInflater
//            .from(parent.context)
//            .inflate(R.layout.task_items, parent, false)
//        return TaskHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
//        val task = tasks[position]
//
//        holder.nameTextView.text = task.name
//        holder.startDateTextView.text = task.startDate
//        holder.endDateTextView.text = task.endDate
//    }
//
//    override fun getItemCount() = tasks.size
//}
