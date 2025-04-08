package com.example.testdag.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.testdag.R
import com.example.testdag.data.model.CartItem
import com.example.testdag.databinding.ItemCartBinding
import com.example.testdag.util.CartDiffUtilCallback

class CartAdapter : ListAdapter<CartItem, CartAdapter.CartViewHolder>(CartDiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        fun bind(cartItem: CartItem) {
            binding.cartItemId.text = cartItem.name ?: "Unknown"
            binding.cartItemQuantity.text = binding.root.context.getString(R.string.cart_item_quantity, cartItem.quantity)
        }
    }
}