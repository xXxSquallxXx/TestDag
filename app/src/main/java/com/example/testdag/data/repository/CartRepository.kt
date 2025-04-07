package com.example.testdag.data.repository

import com.example.testdag.data.database.CartDao
import com.example.testdag.data.model.CartItem
import kotlinx.coroutines.flow.Flow

class CartRepository(private val cartDao: CartDao) {

    fun getAllCartItems(): Flow<List<CartItem>> {
        return cartDao.getAllCartItems()
    }

    suspend fun insert(cartItem: CartItem) {
        cartDao.insert(cartItem)
    }

    suspend fun clearCart() {
        cartDao.clearCart()
    }
}