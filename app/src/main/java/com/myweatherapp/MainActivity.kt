package com.myweatherapp

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.myweatherapp.databinding.ActivityMainBinding
import com.myweatherapp.service.WeatherApi
import com.myweatherapp.ui.main.SectionsPagerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(sectionsPagerAdapter.tabs[position].titleSourceId)
        }.attach()

        showLoader()
        CoroutineScope(Main).launch {
            val data = WeatherApi().getRawData("Gliwice")
            data?.let { AppState.updateData(it) }
            hideLoader()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actions, menu)

        val menuItem = menu?.findItem(R.id.action_search)
        val searchView = menuItem?.actionView as SearchView?
        searchView?.queryHint = "Nazwa miejscowości"
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean = false
            override fun onQueryTextSubmit(query: String): Boolean {
                showLoader()
                menuItem!!.collapseActionView()

                CoroutineScope(Main).launch {
                    val data = WeatherApi().getRawData(query)
                    data?.let { AppState.updateData(it) }
                    hideLoader()
                }
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun showLoader() {
        binding.progressBar.visibility = View.VISIBLE
        binding.viewPager.visibility = View.INVISIBLE
    }

    private fun hideLoader() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.viewPager.visibility = View.VISIBLE
    }
}