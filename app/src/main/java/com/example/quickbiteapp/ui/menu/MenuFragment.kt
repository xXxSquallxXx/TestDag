package com.example.quickbiteapp.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quickbiteapp.databinding.FragmentMenuBinding
import com.example.quickbiteapp.ui.adapter.MenuAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuFragment @Inject constructor(
    private val viewModel: MenuViewModel?,
    private val menuAdapter: MenuAdapter
) : Fragment() {

    private var binding: FragmentMenuBinding? = null
    private var restaurantId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        restaurantId = arguments?.getInt("restaurantId") ?: 0
        menuAdapter.setRestaurantId(restaurantId)
        viewModel?.loadMenu(restaurantId)
        observeMenu()
    }

    fun setRestaurantId(id: Int) {
        restaurantId = id
        menuAdapter.setRestaurantId(restaurantId)
        viewModel?.loadMenu(restaurantId)
    }

    private fun setupRecyclerView() {
        binding?.menuList?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = menuAdapter
        }
    }

    private fun observeMenu() {
        lifecycleScope.launch {
            viewModel?.menuItems?.collect { items ->
                menuAdapter.submitList(items)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}