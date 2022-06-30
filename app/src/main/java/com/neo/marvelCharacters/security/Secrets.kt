package com.neo.marvelCharacters.security

import android.content.Context

object Secrets {

    init {
        System.loadLibrary("secrets")
    }

    external fun getPublicKey(context: Context): String
    external fun getSecretKey(context: Context): String
}