package com.example.quickbiteapp.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quickbiteapp.databinding.FragmentCartBinding
import com.example.quickbiteapp.ui.adapter.CartAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartFragment @Inject constructor(
    private val viewModel: CartViewModel?,
    private val cartAdapter: CartAdapter
) : Fragment() {

    private var binding: FragmentCartBinding? = null

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