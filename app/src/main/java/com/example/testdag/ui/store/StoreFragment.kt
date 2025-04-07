package com.example.testdag.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testdag.data.model.Product
import com.example.testdag.databinding.FragmentStoreBinding
import com.example.testdag.di.MainApplication
import com.example.testdag.ui.adapter.ProductAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class StoreFragment : Fragment() {

    private var binding: FragmentStoreBinding? = null

    @Inject
    var viewModel: StoreViewModel? = null

    private val productAdapter = ProductAdapter { product -> onAddToCart(product) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as MainApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel?.addInitialProducts()
        observeProducts()
    }

    private fun setupRecyclerView() {
        binding?.productList?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productAdapter
        }
    }

    private fun observeProducts() {
        lifecycleScope.launch {
            viewModel?.products?.collect { products ->
                productAdapter.submitList(products)
            }
        }
    }

    private fun onAddToCart(product: Product) {
        viewModel?.addToCart(product)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}