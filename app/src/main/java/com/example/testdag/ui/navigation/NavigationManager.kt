package com.example.testdag.ui.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.testdag.R

object NavigationManager {
    private var fragmentManager: FragmentManager? = null

    fun setFragmentManager(manager: FragmentManager) {
        fragmentManager = manager
    }

    fun replaceFragment(fragment: Fragment) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, fragment)
            ?.commit()
    }
}