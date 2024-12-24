package com.myjar.jarassignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myjar.jarassignment.R
import com.myjar.jarassignment.data.model.ComputerItem
import com.myjar.jarassignment.databinding.ItemCardBinding

class ItemAdapter(
    private val onItemClick: (ComputerItem) -> Unit
) : ListAdapter<ComputerItem, ItemAdapter.ItemViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClick)
    }

    class ItemViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ComputerItem, onItemClick: (ComputerItem) -> Unit) {
            binding.itemName.text = item.name
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }

    class ItemDiffCallback : DiffUtil.ItemCallback<ComputerItem>() {
        override fun areItemsTheSame(oldItem: ComputerItem, newItem: ComputerItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ComputerItem, newItem: ComputerItem): Boolean {
            return oldItem == newItem
        }
    }
}
