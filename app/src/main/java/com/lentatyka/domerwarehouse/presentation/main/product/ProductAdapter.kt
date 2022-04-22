package com.lentatyka.domerwarehouse.presentation.main.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lentatyka.domerwarehouse.databinding.ProductItemBinding
import com.lentatyka.domerwarehouse.domain.main.product.ProductDomain

class ProductAdapter : ListAdapter<ProductDomain, ProductAdapter.ProductViewHolder>(DiffCallback) {

    class ProductViewHolder(
        private val binding: ProductItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ProductDomain){
            binding.product = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<ProductDomain>() {
            override fun areItemsTheSame(oldItem: ProductDomain, newItem: ProductDomain): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ProductDomain,
                newItem: ProductDomain
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}