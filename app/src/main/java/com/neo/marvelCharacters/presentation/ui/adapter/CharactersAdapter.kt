package com.neo.marvelCharacters.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.neo.marvelCharacters.R
import com.neo.marvelCharacters.databinding.ItemMarvelCharacterBinding
import com.neo.marvelCharacters.domain.model.MarvelCharacter
import com.neo.marvelCharacters.util.extensions.dp
import com.neo.marvelCharacters.util.extensions.updateMargins

class CharactersAdapter(
    differCallback: DiffUtil.ItemCallback<MarvelCharacter>
) : PagingDataAdapter<MarvelCharacter, CharactersAdapter.Holder>(differCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemMarvelCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val character = getItem(position)

        holder.isPlaceHolder = character == null

        character?.run {
            holder.bind(character)
        }

        holder.isLastItem = position == itemCount.dec()
    }

    class Holder(
        binding: ItemMarvelCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val row = binding.row
        private val shimmer = binding.shimmer

        private val context get() = itemView.context

        var isPlaceHolder: Boolean = false
            set(value) {
                field = value
                row.root.isVisible = !value
                shimmer.root.isVisible = value
            }

        var isLastItem: Boolean = false
            set(value) {
                field = value
                itemView.updateMargins(
                    bottom = if (value) 4.dp.toPx(context).toInt() else 0
                )
            }

        fun bind(character: MarvelCharacter) {
            row.ivThumbnail.load(character.thumbnail.url) {
                placeholder(R.drawable.ic_image)
                error(R.drawable.ic_broken_image)
            }
            row.tvName.text = character.name
            row.tvDescription.text = character.description
        }
    }
}

