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
import com.ssu.taskmaster.databinding.FragmentAllTasksBinding
import com.ssu.taskmaster.db.TaskHelperDb
import com.ssu.taskmaster.models.Task
import com.ssu.taskmaster.models.TaskAdapter
import com.ssu.taskmaster.service.TaskService

class AllTasksFragment : Fragment() {

    private lateinit var binding: FragmentAllTasksBinding

    private lateinit var taskAdapter: TaskAdapter
    private lateinit var listItem: RecyclerView

    private lateinit var taskService: TaskService

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllTasksBinding.inflate(inflater, container, false)

        val taskDb = TaskHelperDb.getConnect(requireActivity())
        taskService = TaskService(taskDb)

        listItem = binding.listItem

        listItem.layoutManager = LinearLayoutManager(context)

        taskAdapter = TaskAdapter(
            requireContext(),
            object : TaskAdapter.OnExecuteClickListener {
                override fun onExecuteClick(task: Task) {
                    Thread {
                        taskDb.getDao().updateTaskCompletionStatus(task.id, !task.isCompleted)
                    }.start()
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
                    Thread {
                        taskDb.getDao().deleteTask(task)
                    }.start()
                }
            })


        listItem.adapter = taskAdapter

        taskService.getAllTasks().observe(viewLifecycleOwner) { tasks ->
            taskAdapter.setTasks(tasks)
            taskAdapter.notifyDataSetChanged()
        }

        binding.addTaskButton.setOnClickListener {
            launchNewActivity()
        }

        return binding.root
    }

    private fun launchNewActivity() {
        val intent = Intent(activity, CreateTaskActivity::class.java)
        startActivity(intent)
    }
}