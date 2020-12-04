package com.example.midterm2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.midterm2.adapter.ChildFragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        val sectionsPagerAdapter = ChildFragmentPagerAdapter(this)
        val viewPager = viewPagerId
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = tabLayout
        TabLayoutMediator(tabs, viewPager, TabConfiguration()).attach()
    }

    private class TabConfiguration : TabLayoutMediator.TabConfigurationStrategy {
        override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
            val tabNames = listOf("Primary", "Secondary", "Tertiary")
            tab.text = tabNames[position]
        }
    }
}