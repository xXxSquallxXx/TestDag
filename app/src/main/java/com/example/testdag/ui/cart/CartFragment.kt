package com.example.testdag.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testdag.databinding.FragmentCartBinding
import com.example.testdag.ui.adapter.CartAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartFragment @Inject constructor(
    private val viewModel: CartViewModel?
) : Fragment() {

    private var binding: FragmentCartBinding? = null
    private val cartAdapter = CartAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeCartItems()
        setupClearCartButton()
    }

    private fun setupRecyclerView() {
        binding?.cartList?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartAdapter
        }
    }

    private fun setupClearCartButton() {
        binding?.clearCartButton?.setOnClickListener {
            viewModel?.clearCart()
        }
    }

    private fun observeCartItems() {
        lifecycleScope.launch {
            viewModel?.cartItems?.collect { items ->
                cartAdapter.submitList(items)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}