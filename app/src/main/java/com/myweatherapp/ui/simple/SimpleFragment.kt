package com.myweatherapp.ui.simple

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myweatherapp.AppState
import com.myweatherapp.R
import com.myweatherapp.databinding.SimpleFragmentBinding
import java.net.URL
import java.util.*
import kotlin.math.roundToInt


class SimpleFragment : Fragment() {
    private var _binding: SimpleFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = SimpleFragmentBinding.inflate(inflater, container, false)
        val root = binding.root

        AppState.data.observe(viewLifecycleOwner) {
            Thread {
                val url = URL("https://openweathermap.org/img/wn/${it.weather[0].icon}@2x.png")
                val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                requireActivity().runOnUiThread {
                    binding.icon.setImageBitmap(bmp)
                }
            }.start()

            val timeTf = java.text.SimpleDateFormat("HH:mm", Locale.GERMANY)
            val dateTimeTf = java.text.SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.GERMANY)

            binding.cityName.text = getString(R.string.simple_city_name, it.name)
            binding.desc.text = getString(R.string.simple_desc, it.weather[0].description.replaceFirstChar { c -> c.uppercase() })
            binding.temp.text = getString(R.string.simple_temp, it.main.temp.roundToInt())
            binding.pressure.text = getString(R.string.simple_pressure, it.main.pressure)
            binding.sunrise.text = getString(
                R.string.simple_sunrise,
                timeTf.format(Date(it.sys.sunrise * 1000))
            )
            binding.sunset.text = getString(
                R.string.simple_sunset,
                timeTf.format(Date(it.sys.sunset * 1000))
            )
            binding.readData.text = getString(
                R.string.simple_read_data,
                dateTimeTf.format(Date(it.dt * 1000))
            )
        }

        return root
    }
}