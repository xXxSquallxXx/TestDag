package com.example.testdag.ui.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testdag.data.repository.CartRepository
import com.example.testdag.data.repository.ProductRepository
import com.example.testdag.data.model.CartItem
import com.example.testdag.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class StoreViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    init {
        viewModelScope.launch {
            productRepository.getAllProducts().collect { productList ->
                _products.value = productList
            }
        }
    }

    fun addInitialProducts() {
        viewModelScope.launch {
            if (products.value.isEmpty()) {
                productRepository.insert(Product(name = "Item 1", price = 10.0))
                productRepository.insert(Product(name = "Item 2", price = 20.0))
            }
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            cartRepository.insert(CartItem(productId = product.id, quantity = 1))
        }
    }
}