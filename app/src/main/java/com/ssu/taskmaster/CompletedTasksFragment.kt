package com.ssu.taskmaster

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssu.taskmaster.databinding.FragmentCompletedTasksBinding
import com.ssu.taskmaster.db.TaskHelperDb
import com.ssu.taskmaster.models.Task
import com.ssu.taskmaster.models.TaskAdapter
import com.ssu.taskmaster.service.TaskService

class CompletedTasksFragment : Fragment() {

    private lateinit var binding: FragmentCompletedTasksBinding

    private lateinit var taskAdapter: TaskAdapter
    private lateinit var listItem: RecyclerView

    private lateinit var taskService: TaskService

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompletedTasksBinding.inflate(inflater, container, false)

        val taskDb = TaskHelperDb.getConnect(requireActivity())
        taskService = TaskService(taskDb)

        listItem = binding.listItem

        listItem.layoutManager = LinearLayoutManager(context)
        taskAdapter = TaskAdapter(
            requireContext(),
            object : TaskAdapter.OnExecuteClickListener {
                override fun onExecuteClick(task: Task) {
                    taskService.updateTaskCompletionStatus(task, !task.isCompleted)
                }
            },
            object : TaskAdapter.OnEditClickListener {
                override fun onEditClick(task: Task) {
                    Thread {
                        // TODO: переделать
                        val foundTask = taskDb.getDao().getTaskByName(task.name)
                        println("Task: $foundTask")
                    }.start()
                }

            },
            object : TaskAdapter.OnDeleteClickListener {
                override fun onDeleteClick(task: Task) {
                    taskService.deleteTask(task)
                }
            })

        listItem.adapter = taskAdapter

        taskService.getCompletedTasks().observe(viewLifecycleOwner) { tasks ->
            taskAdapter.setTasks(tasks)
            taskAdapter.notifyDataSetChanged()
        }

        return binding.root
    }
}