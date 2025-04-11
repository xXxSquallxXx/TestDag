package com.example.quickbiteapp.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.quickbiteapp.R
import com.example.quickbiteapp.data.model.CartItem
import com.example.quickbiteapp.data.model.MenuItem
import com.example.quickbiteapp.data.repository.CartRepository
import com.example.quickbiteapp.databinding.FragmentMenuItemBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuItemFragment @Inject constructor(
    private val cartRepository: CartRepository
) : Fragment() {

    private var binding: FragmentMenuItemBinding? = null
    private var menuItem: MenuItem? = null
    private var restaurantId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuItemBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.toolbar?.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        menuItem?.let { item ->
            binding?.menuItemImage?.load(item.imageUrl)
            binding?.menuItemName?.text = item.name
            binding?.menuItemDescription?.text = item.description
            binding?.menuItemPrice?.text = String.format(
                getString(R.string.product_price_format),
                item.price
            )
        }

        binding?.addToCartButton?.setOnClickListener {
            menuItem?.let { item ->
                lifecycleScope.launch {
                    val uniqueProductId = restaurantId * 100 + item.id // Уникальный productId
                    cartRepository.insert(CartItem(restaurantId = restaurantId, productId = uniqueProductId, quantity = 1, name = item.name))
                }
            }
        }
    }

    fun setMenuItem(item: MenuItem, restaurantId: Int) {
        this.menuItem = item
        this.restaurantId = restaurantId
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}