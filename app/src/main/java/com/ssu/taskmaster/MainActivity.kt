package com.ssu.taskmaster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    private var viewPager: ViewPager2? = null
    private var tabLayout: TabLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tabs)

        fragmentInit()
    }

    private fun fragmentInit() {
        val fragments: MutableList<Fragment> = ArrayList()
        fragments.add(AllTasksFragment())
        fragments.add(ActiveTasksFragment())
        fragments.add(CompletedTasksFragment())

        val adapter = FragmentAdapter(this, fragments)
        viewPager!!.adapter = adapter

        TabLayoutMediator(
            tabLayout!!, viewPager!!
        ) { tab: TabLayout.Tab, position: Int ->
            when (position) {
                0 -> tab.text = "Все задачи"
                1 -> tab.text = "Активные задачи"
                2 -> tab.text = "Завершённые задачи"
            }
        }.attach()
    }
}