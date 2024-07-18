package com.example.vimos.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.vimos.R
import com.example.vimos.databinding.CategoryItemBinding
import com.example.vimos.domain.models.FirstLevelCategories

class Adapter (private val onClick: (String) -> Unit) :
    ListAdapter<FirstLevelCategories, Adapter.ViewHolder>(DiffUtilCategories()) {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding by viewBinding{ CategoryItemBinding.bind(itemView) }

        fun bind(categories: FirstLevelCategories) {
            binding.textView.text = categories.title
            binding.root.setOnClickListener{
                onClick.invoke(categories.title)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = currentList[position]
        holder.bind(category)
    }

    private class DiffUtilCategories : DiffUtil.ItemCallback<FirstLevelCategories>() {

        override fun areItemsTheSame(oldItem: FirstLevelCategories, newItem: FirstLevelCategories): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FirstLevelCategories, newItem: FirstLevelCategories): Boolean {
            return oldItem.title == newItem.title
        }
    }
}