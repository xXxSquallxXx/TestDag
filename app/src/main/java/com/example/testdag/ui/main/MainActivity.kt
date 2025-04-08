package com.example.testdag.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testdag.databinding.ActivityMainBinding
import com.example.testdag.di.MainApplication
import com.example.testdag.util.UiInitializer

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { MainViewModel(application as MainApplication) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val appComponent = (application as MainApplication).appComponent
        UiInitializer.initUi(this, binding, appComponent)

        viewModel.initProducts()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.resetFirstRun()
    }
}