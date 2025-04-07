package com.example.testdag.data.repository

import com.example.testdag.data.database.ProductDao
import com.example.testdag.data.model.Product
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {

    fun getAllProducts(): Flow<List<Product>> {
        return productDao.getAllProducts()
    }

    suspend fun insert(product: Product) {
        productDao.insert(product)
    }
}