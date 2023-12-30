package com.kobylynskyiv.taskmanager.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kobylynskyiv.taskmanager.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

}
