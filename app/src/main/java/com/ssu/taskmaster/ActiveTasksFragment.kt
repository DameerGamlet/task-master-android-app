package com.ssu.taskmaster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ssu.taskmaster.databinding.FragmentActiveTasksBinding

class ActiveTasksFragment : Fragment() {

    private lateinit var binding: FragmentActiveTasksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActiveTasksBinding.inflate(inflater, container, false)
        return binding.root
    }
}