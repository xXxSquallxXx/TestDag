package com.example.testdag.util

import androidx.fragment.app.FragmentActivity
import com.example.testdag.databinding.ActivityMainBinding
import com.example.testdag.di.AppComponent
import com.example.testdag.ui.navigation.NavigationManager

object UiInitializer {
    fun initUi(activity: FragmentActivity, binding: ActivityMainBinding, appComponent: AppComponent) {
        NavigationManager.setFragmentManager(activity.supportFragmentManager)
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                com.example.testdag.R.id.nav_store -> {
                    NavigationManager.replaceFragment(appComponent.storeFragment())
                    true
                }
                com.example.testdag.R.id.nav_cart -> {
                    NavigationManager.replaceFragment(appComponent.cartFragment())
                    true
                }
                else -> false
            }
        }
        NavigationManager.replaceFragment(appComponent.storeFragment())
    }
}