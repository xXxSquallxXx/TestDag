package com.example.quickbiteapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quickbiteapp.databinding.ActivityMainBinding
import com.example.quickbiteapp.di.MainApplication
import com.example.quickbiteapp.util.UiInitializer

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val appComponent = (application as MainApplication).appComponent
        UiInitializer.initUi(this, binding, appComponent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}