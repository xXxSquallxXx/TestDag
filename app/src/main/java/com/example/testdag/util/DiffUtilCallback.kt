package com.example.testdag.util

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.testdag.data.model.CartItem
import com.example.testdag.data.model.Product

class DiffUtilCallback : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return when {
            oldItem is Product && newItem is Product -> oldItem.id == newItem.id
            oldItem is CartItem && newItem is CartItem -> oldItem.id == newItem.id
            else -> false
        }
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem == newItem
    }
}