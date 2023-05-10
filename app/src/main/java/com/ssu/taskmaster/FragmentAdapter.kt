package com.ssu.taskmaster

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(activity: MainActivity, fragments: List<Fragment>) :
    FragmentStateAdapter(activity) {
    private val fragmentList = fragments
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}