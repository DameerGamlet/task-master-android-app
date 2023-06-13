package com.ssu.taskmaster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssu.taskmaster.components.dialogs.TaskInfoDialogFragment
import com.ssu.taskmaster.databinding.FragmentCompletedTasksBinding
import com.ssu.taskmaster.db.TaskHelperDb
import com.ssu.taskmaster.models.Task
import com.ssu.taskmaster.models.TaskAdapter
import com.ssu.taskmaster.service.TaskService

class CompletedTasksFragment : Fragment(), TaskAdapter.OnInfoClickListener {

    private lateinit var binding: FragmentCompletedTasksBinding

    private lateinit var taskAdapter: TaskAdapter
    private lateinit var listItem: RecyclerView

    private lateinit var taskService: TaskService

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
            this,
            object : TaskAdapter.OnDeleteClickListener {
                override fun onDeleteClick(task: Task) {
                    Thread {
                        taskDb.getDao().deleteTask(task)
                    }.start()
                }
            })

        listItem.adapter = taskAdapter

        return binding.root
    }

    override fun onInfoClick(task: Task) {
        val taskDb = TaskHelperDb.getConnect(requireActivity())
        Thread {
            val foundTask = taskDb.getDao().getTaskByName(task.name)
            activity?.runOnUiThread {
                foundTask?.let { TaskInfoDialogFragment(it) }
                    ?.show(childFragmentManager, "task_info_dialog")
            }
        }.start()
    }

    override fun onResume() {
        super.onResume()
        updateCompletedTasksFragment()
    }

    private fun updateCompletedTasksFragment() {
        taskService.getCompletedTasks().observe(viewLifecycleOwner) { tasks ->
            taskAdapter.setTasks(tasks)
            taskAdapter.notifyDataSetChanged()
        }
    }
}
