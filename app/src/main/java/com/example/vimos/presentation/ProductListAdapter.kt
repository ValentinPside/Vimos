package com.example.vimos.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.vimos.R
import com.example.vimos.databinding.ProductItemBinding
import com.example.vimos.domain.models.Product
import com.example.vimos.utils.APIConstants.BASE_IMAGE_URL

class ProductListAdapter (private val onClick: (slug: String) -> Unit) :
    ListAdapter<Product, ProductListAdapter.ViewHolder>(DiffUtilCategories()) {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding by viewBinding{ ProductItemBinding.bind(itemView) }

        fun bind(product: Product) {
            binding.article.text = product.sku.toString()
            binding.title.text = product.title
            binding.price.text = product.purchase.price.toString()
            Glide.with(itemView)
                .load(BASE_IMAGE_URL.plus(product.images[0].original))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.imageView)
            binding.root.setOnClickListener{
                onClick.invoke(product.slug)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = currentList[position]
        holder.bind(product)
    }

    private class DiffUtilCategories : DiffUtil.ItemCallback<Product>() {

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.title == newItem.title
        }
    }
}