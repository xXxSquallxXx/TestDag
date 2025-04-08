package com.example.testdag.util

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.testdag.data.model.CartItem
import com.example.testdag.data.model.Product

abstract class DiffUtilCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}

object CartDiffUtilCallback : DiffUtilCallback<CartItem>() {
    override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
        return oldItem.id == newItem.id
    }
}

object ProductDiffUtilCallback : DiffUtilCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }
}