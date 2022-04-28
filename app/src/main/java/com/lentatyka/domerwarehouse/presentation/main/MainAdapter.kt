package com.lentatyka.domerwarehouse.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lentatyka.domerwarehouse.R
import com.lentatyka.domerwarehouse.data.main.room.ProductData
import com.lentatyka.domerwarehouse.databinding.ProductItemBinding

class MainAdapter : PagingDataAdapter<ProductData, MainAdapter.ProductViewHolder>(DiffCallback) {

    class ProductViewHolder(
        private val binding: ProductItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ProductData){
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
        getItem(position)?.let {
            val color = if(position % 2 == 0){
                R.color.beige_100
            }else
                R.color.beige_50

            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, color))
            holder.bind(it)
        }
    }



    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<ProductData>() {
            override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ProductData,
                newItem: ProductData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}