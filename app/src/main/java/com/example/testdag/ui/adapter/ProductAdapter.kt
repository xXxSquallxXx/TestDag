package com.example.testdag.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.testdag.data.model.Product
import com.example.testdag.databinding.ItemProductBinding
import com.example.testdag.R

class ProductAdapter(private val onAddToCart: (Product) -> Unit) :
    ListAdapter<Product, ProductAdapter.ProductViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.productName.text = product.name
            binding.productPrice.text = binding.root.context.getString(R.string.product_price_format, product.price)
            binding.addToCartButton.setOnClickListener {
                onAddToCart(product)
            }
        }
    }

    companion object DiffUtilCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}