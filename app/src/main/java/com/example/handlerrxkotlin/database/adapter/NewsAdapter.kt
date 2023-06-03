package com.example.handlerrxkotlin.database.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.handlerrxkotlin.database.entity.NewsEntity
import com.example.handlerrxkotlin.databinding.ItemNewsBinding

class NewsAdapter : ListAdapter<NewsEntity, NewsAdapter.Vh>(MyDiffUtil()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.bind(getItem(position))
    }

    inner class Vh(var binding: ItemNewsBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(newsEntity: NewsEntity){
            binding.text1.text = newsEntity.title
            binding.text2.text = newsEntity.description
        }
    }

    class MyDiffUtil: DiffUtil.ItemCallback<NewsEntity>(){
        override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
            return oldItem == newItem
        }
    }

}