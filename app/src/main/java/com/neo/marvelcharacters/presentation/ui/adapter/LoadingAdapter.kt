package com.neo.marvelcharacters.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.neo.marvelcharacters.databinding.ItemLoadingBinding

class LoadingAdapter : LoadStateAdapter<LoadingAdapter.LoadingHolder>() {
    override fun onBindViewHolder(holder: LoadingHolder, loadState: LoadState) = Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingHolder {
        return LoadingHolder(
            ItemLoadingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class LoadingHolder(
        binding: ItemLoadingBinding
    ) : RecyclerView.ViewHolder(binding.root)
}