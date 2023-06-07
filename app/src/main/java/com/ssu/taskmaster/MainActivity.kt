package com.ssu.taskmaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ssu.taskmaster.models.FragmentAdapter

class MainActivity : AppCompatActivity() {
    private var viewPager: ViewPager2? = null
    private var tabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tabs)

        fragmentInit()

        // Добавляем слушатель изменения вкладок
        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                // При переключении на другую вкладку вызываем метод обновления компонентов
                updateComponents(position)
            }
        })
    }

    private fun fragmentInit() {
        val fragments: MutableList<Fragment> = ArrayList()
        fragments.add(AllTasksFragment())
        fragments.add(ActiveTasksFragment())
        fragments.add(CompletedTasksFragment())
        fragments.add(SettingsFragment())

        val adapter = FragmentAdapter(this, fragments)
        viewPager!!.adapter = adapter

        TabLayoutMediator(
            tabLayout!!, viewPager!!
        ) { tab: TabLayout.Tab, position: Int ->
            when (position) {
                0 -> tab.text = "Все задачи"
                1 -> tab.text = "Актив. задачи"
                2 -> tab.text = "Заверш. задачи"
                3 -> tab.text = "Настройки"
            }
        }.attach()
    }

    private fun updateComponents(position: Int) {
        // Здесь вызывайте методы обновления компонентов в соответствии с текущей вкладкой
        when (position) {
            0 -> updateAllTasksFragment()
            1 -> updateActiveTasksFragment()
            2 -> updateCompletedTasksFragment()
            3 -> updateSettingsFragment()
        }
    }

    private fun updateAllTasksFragment() {
        // Обновление компонентов во фрагменте "Все задачи"
    }

    private fun updateActiveTasksFragment() {
        // Обновление компонентов во фрагменте "Активные задачи"
    }

    private fun updateCompletedTasksFragment() {
        // Обновление компонентов во фрагменте "Завершенные задачи"
    }

    private fun updateSettingsFragment() {
        // Обновление компонентов во фрагменте "Настройки"
    }
}