package com.emanuel.pulsusnorris.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.pulsusnorris.databinding.ItemListMainBinding

class HomeMainAdapter(
    private val items: List<HomeMainItem>,
    private val listener: HomeItemClickListener
) : RecyclerView.Adapter<HomeMainAdapter.MainViewHolder>() {

    private lateinit var binding: ItemListMainBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        binding = ItemListMainBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class MainViewHolder(item: ItemListMainBinding) : RecyclerView.ViewHolder(item.root) {

        fun bind(item: HomeMainItem) {
            binding.apply {
                itemImageIv.setImageResource(item.image)
                itemValueTv.setText(item.text)

                root.setOnClickListener {
                    listener.onClick(item)
                }
            }
        }
    }
}