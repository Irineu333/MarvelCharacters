package com.neo.marvelcharacters.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.neo.marvelcharacters.R
import com.neo.marvelcharacters.databinding.ItemMarvelCharacterBinding
import com.neo.marvelcharacters.domain.model.MarvelCharacter

class CharactersAdapter(
    differCallback: DiffUtil.ItemCallback<MarvelCharacter>
) : PagingDataAdapter<MarvelCharacter, CharactersAdapter.Holder>(differCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemMarvelCharacterBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val character = getItem(position) ?: return

        holder.bind(character)
    }

    class Holder(
        private val binding: ItemMarvelCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: MarvelCharacter) {
            binding.ivThumbnail.load(character.thumbnail.url)
            binding.tvName.text = character.name
        }
    }
}