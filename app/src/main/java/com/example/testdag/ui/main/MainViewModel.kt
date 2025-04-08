package com.example.testdag.ui.main

import androidx.lifecycle.ViewModel
import com.example.testdag.data.database.AppDatabase
import com.example.testdag.data.repository.CartRepository
import com.example.testdag.data.repository.ProductRepository
import com.example.testdag.di.MainApplication
import com.example.testdag.ui.store.StoreViewModel

class MainViewModel(private val application: MainApplication) : ViewModel() {

    private val storeViewModel: StoreViewModel by lazy {
        val database = AppDatabase.getDatabase(application)
        val productRepository = ProductRepository(database.productDao())
        val cartRepository = CartRepository(database.cartDao())
        StoreViewModel(productRepository, cartRepository)
    }

    fun initProducts() {
        storeViewModel.addInitialProducts(application)
    }
}