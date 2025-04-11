package com.example.quickbiteapp.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.quickbiteapp.databinding.ActivityMainBinding
import com.example.quickbiteapp.di.AppComponent
import com.example.quickbiteapp.ui.cart.CartFragment
import com.example.quickbiteapp.ui.menu.MenuFragment
import com.example.quickbiteapp.ui.menu.MenuItemFragment
import com.example.quickbiteapp.ui.navigation.NavigationManager
import com.example.quickbiteapp.ui.restaurant.RestaurantListFragment
import com.example.quickbiteapp.R

object UiInitializer {
    fun initUi(activity: FragmentActivity, binding: ActivityMainBinding, appComponent: AppComponent) {
        NavigationManager.setFragmentManager(activity.supportFragmentManager)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val currentFragment = getCurrentFragment(activity)
            when (item.itemId) {
                R.id.nav_store -> {
                    if (!isStoreRelatedFragment(currentFragment)) {
                        NavigationManager.replaceFragmentForRestaurant(appComponent.restaurantListFragment())
                    }
                    true
                }
                R.id.nav_cart -> {
                    if (currentFragment !is CartFragment) {
                        NavigationManager.replaceFragmentForRestaurant(appComponent.cartFragment())
                    }
                    true
                }
                else -> false
            }
        }

        activity.supportFragmentManager.addOnBackStackChangedListener {
            NavigationManager.onBackStackChanged()
            val currentFragment = getCurrentFragment(activity)
            binding.bottomNavigation.selectedItemId = when (currentFragment) {
                is RestaurantListFragment, is MenuFragment, is MenuItemFragment -> R.id.nav_store
                is CartFragment -> R.id.nav_cart
                else -> R.id.nav_store
            }
        }

        NavigationManager.replaceFragmentForRestaurant(appComponent.restaurantListFragment())
    }

    private fun getCurrentFragment(activity: FragmentActivity): Fragment? {
        return activity.supportFragmentManager.fragments.lastOrNull()
    }

    private fun isStoreRelatedFragment(fragment: Fragment?): Boolean {
        return fragment is RestaurantListFragment || fragment is MenuFragment || fragment is MenuItemFragment
    }
}