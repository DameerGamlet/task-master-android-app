package com.ssu.taskmaster

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import com.ssu.taskmaster.databinding.FragmentAllTasksBinding
import com.ssu.taskmaster.db.TaskHelperDb
import com.ssu.taskmaster.models.TaskAdapter

class AllTasksFragment : Fragment() {

    private lateinit var binding: FragmentAllTasksBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllTasksBinding.inflate(inflater, container, false)

        val taskDb = TaskHelperDb.getConnect(requireActivity());
        val adapter = TaskAdapter(emptyList())
        binding.listItem.adapter = adapter

        taskDb.getDao().getAllTask().asLiveData().observe(requireActivity()) { tasks ->
            adapter.tasks = tasks
            adapter.notifyDataSetChanged()
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