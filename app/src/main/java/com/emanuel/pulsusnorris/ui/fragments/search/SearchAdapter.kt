package com.emanuel.pulsusnorris.ui.fragments.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.pulsusnorris.data.model.local.Joke
import com.emanuel.pulsusnorris.databinding.ItemListSearchBinding

class SearchAdapter(
    private val jokes: List<Joke>
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private lateinit var binding: ItemListSearchBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        binding = ItemListSearchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(jokes[position])
    }

    override fun getItemCount(): Int = jokes.size

    inner class SearchViewHolder(itemBinding: ItemListSearchBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Joke) {
            binding.run {
                itemValueTv.text = item.value
            }
        }
    }
}