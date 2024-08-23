package com.example.android.bintest.historybin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.bintest.database.CardInfoEntity
import com.example.android.bintest.databinding.SearchHistoryItemBinding

class HistoryBinAdapter() :
    ListAdapter<CardInfoEntity, HistoryBinAdapter.ViewHolder>(HistoryBinAdapterDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(val binding: SearchHistoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CardInfoEntity) {
            binding.cardItem = item
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SearchHistoryItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class HistoryBinAdapterDiffCallback() : DiffUtil.ItemCallback<CardInfoEntity>() {
        override fun areItemsTheSame(oldItem: CardInfoEntity, newItem: CardInfoEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CardInfoEntity, newItem: CardInfoEntity): Boolean {
            return oldItem == newItem
        }
    }
}