package com.myweatherapp.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.myweatherapp.R
import com.myweatherapp.model.TabViewModel
import com.myweatherapp.ui.normal.NormalFragment
import com.myweatherapp.ui.simple.SimpleFragment

class SectionsPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    var tabs = arrayOf(
        TabViewModel(R.string.view_1_title, NormalFragment()),
        TabViewModel(R.string.view_2_title, SimpleFragment())
    )

    override fun getItemCount(): Int = tabs.size

    override fun createFragment(position: Int): Fragment {
        return tabs[position].fragment
    }
}