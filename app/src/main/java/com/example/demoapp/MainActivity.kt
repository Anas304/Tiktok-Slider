package com.example.demoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.ViewPagerVideoAdapter
import com.example.demoapp.databinding.ActivityMainBinding
import com.example.demoapp.network.RetrofitService.retrofitService
import com.example.demoapp.network.model.Video
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val videoList = MutableLiveData<List<Video>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            val result = retrofitService.getData().await()
            videoList.value = result
        }

        videoList.observe(this, {
            binding.videoViewPager.adapter = ViewPagerVideoAdapter(it)
        })

    }
}