package com.example.testdag.ui.main

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.testdag.databinding.ActivityMainBinding
import com.example.testdag.util.UiInitializer

class MainActivity : FragmentActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        UiInitializer.initUi(this, binding)
    }
}