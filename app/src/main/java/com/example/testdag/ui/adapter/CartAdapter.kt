package com.example.testdag.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.testdag.data.model.CartItem
import com.example.testdag.databinding.ItemCartBinding
import com.example.testdag.R

class CartAdapter : ListAdapter<CartItem, CartAdapter.CartViewHolder>(DiffUtilCallback) {

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
            binding.cartItemId.text = binding.root.context.getString(R.string.cart_item_id, cartItem.productId)
            binding.cartItemQuantity.text = binding.root.context.getString(R.string.cart_item_quantity, cartItem.quantity)
        }
    }

    companion object DiffUtilCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }
    }
}