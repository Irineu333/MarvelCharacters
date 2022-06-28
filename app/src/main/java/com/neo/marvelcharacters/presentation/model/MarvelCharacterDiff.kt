package com.neo.marvelCharacters.presentation.model

import androidx.recyclerview.widget.DiffUtil
import com.neo.marvelCharacters.domain.model.MarvelCharacter

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
