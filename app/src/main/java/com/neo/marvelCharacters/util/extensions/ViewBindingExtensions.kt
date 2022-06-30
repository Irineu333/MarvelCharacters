package com.neo.marvelCharacters.util.extensions

import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

fun ViewBinding.showSnackbar(
    message : String,
    length : Int = Snackbar.LENGTH_LONG
): Snackbar {
    return Snackbar.make(
        root, message,
        length
    ).apply {
        show()
    }
}