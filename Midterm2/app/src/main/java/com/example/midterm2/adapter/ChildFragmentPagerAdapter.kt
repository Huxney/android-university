package com.example.midterm2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.midterm2.fragment.FirstFragment
import com.example.midterm2.fragment.SecondFragment
import com.example.midterm2.fragment.ThirdFragment

class ChildFragmentPagerAdapter(
    fragmentActivity: FragmentActivity?
) : FragmentStateAdapter(fragmentActivity!!) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return FirstFragment()
            1 -> return SecondFragment()
            2 -> return ThirdFragment()
        }
        return FirstFragment()
    }

    override fun getItemCount(): Int {
        return 3
    }
}