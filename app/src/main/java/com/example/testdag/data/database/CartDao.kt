package com.example.testdag.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.testdag.data.model.CartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT cart_item.id, cart_item.productId, cart_item.quantity, product.name AS name FROM cart_item JOIN product ON cart_item.productId = product.id")
    fun getAllCartItems(): Flow<List<CartItem>>

    @Insert
    suspend fun insert(cartItem: CartItem)

    @Query("DELETE FROM cart_item")
    suspend fun clearCart()
}