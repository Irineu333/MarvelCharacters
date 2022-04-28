package com.neo.marvelcharacters.presentation.model

import androidx.recyclerview.widget.DiffUtil
import com.neo.marvelcharacters.domain.model.MarvelCharacter

class MarvelCharacterDiff : DiffUtil.ItemCallback<MarvelCharacter>() {
    override fun areItemsTheSame(
        oldItem: MarvelCharacter,
        newItem: MarvelCharacter
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: MarvelCharacter,
        newItem: MarvelCharacter
    ) = oldItem == newItem
}
