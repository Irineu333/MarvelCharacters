package com.neo.marvelCharacters.util.extensions

import android.view.View
import android.view.ViewGroup
import androidx.annotation.Px
import androidx.core.view.*

fun View.updateMargins(
    @Px end: Int = marginLeft,
    @Px top: Int = marginTop,
    @Px start: Int = marginStart,
    @Px bottom: Int = marginBottom
) {
    (layoutParams as ViewGroup.MarginLayoutParams).updateMargins(
        left = end,
        top = top,
        right = start,
        bottom = bottom
    )
}