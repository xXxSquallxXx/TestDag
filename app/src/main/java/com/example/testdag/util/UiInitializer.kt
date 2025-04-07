package com.example.testdag.util

import androidx.fragment.app.FragmentActivity
import com.example.testdag.databinding.ActivityMainBinding
import com.example.testdag.ui.cart.CartFragment
import com.example.testdag.ui.navigation.NavigationManager
import com.example.testdag.ui.store.StoreFragment

object UiInitializer {
    fun initUi(activity: FragmentActivity, binding: ActivityMainBinding) {
        NavigationManager.setFragmentManager(activity.supportFragmentManager)
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                com.example.testdag.R.id.nav_store -> {
                    NavigationManager.replaceFragment(StoreFragment())
                    true
                }
                com.example.testdag.R.id.nav_cart -> {
                    NavigationManager.replaceFragment(CartFragment())
                    true
                }
                else -> false
            }
        }
        NavigationManager.replaceFragment(StoreFragment())
    }
}