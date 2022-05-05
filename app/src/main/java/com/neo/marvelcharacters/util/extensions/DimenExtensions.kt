package com.neo.marvelcharacters.util.extensions

import android.content.Context
import android.content.res.Resources
import com.neo.marvelcharacters.R

val Int.dp get() = Dp(this)

data class Dp(
    val value: Number
) {
    fun toPx(resources: Resources): Float {
        return value.toFloat() * resources.getDimension(R.dimen.dimen_1dp)
    }

    fun toPx(context: Context): Float {
        return toPx(context.resources)
    }
}