package com.ssu.taskmaster

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class AllTasksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_all_tasks, container, false)
        val addTaskButton = view.findViewById<Button>(R.id.add_task_button)

        addTaskButton.setOnClickListener {
            launchNewActivity()
//            val intent = Intent(activity, CreateTaskActivity::class.java)
//            startActivity(intent)
        }

        return view
    }
    private fun launchNewActivity() {
        val intent = Intent(activity, CreateTaskActivity::class.java)
        startActivity(intent)
    }
}