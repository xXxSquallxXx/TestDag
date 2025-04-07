package com.example.testdag.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class CartItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: Int,
    val quantity: Int
)