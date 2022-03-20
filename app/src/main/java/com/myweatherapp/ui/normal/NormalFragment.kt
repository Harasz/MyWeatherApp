package com.myweatherapp.ui.normal

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myweatherapp.AppState
import com.myweatherapp.R
import com.myweatherapp.databinding.NormalFragmentBinding
import java.net.URL
import java.util.*
import kotlin.math.roundToInt

class NormalFragment : Fragment() {
    private var _binding: NormalFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = NormalFragmentBinding.inflate(inflater, container, false)
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

            Log.i("max", it.main.temp_max.toString())
            Log.i("min", it.main.temp_min.toString())
            binding.cityName.text = getString(R.string.normal_city_name, it.name)
            binding.desc.text = getString(R.string.normal_desc, it.weather[0].description.replaceFirstChar { c -> c.uppercase() })
            binding.temp.text = getString(R.string.normal_temp, it.main.temp.roundToInt())
            binding.tempMax.text = getString(R.string.normal_temp_max, it.main.temp_max.roundToInt())
            binding.tempMin.text = getString(R.string.normal_temp_min, it.main.temp_min.roundToInt())
            binding.sunrise.text = getString(R.string.normal_sunrise, timeTf.format(Date(it.sys.sunrise * 1000)))
            binding.sunset.text = getString(R.string.normal_sunset, timeTf.format(Date(it.sys.sunset * 1000)))
            binding.tempFeelsLike.text = getString(R.string.normal_temp_feels_like, it.main.feels_like.roundToInt())
            binding.pressure.text = getString(R.string.normal_pressure, it.main.pressure)
            binding.readDate.text = getString(
                R.string.normal_read_data,
                dateTimeTf.format(Date(it.dt * 1000))
            )
        }

        return root
    }
}