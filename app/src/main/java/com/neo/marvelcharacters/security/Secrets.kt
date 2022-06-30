package com.neo.marvelCharacters.security

import android.content.Context

class Secrets {

    companion object {
        init {
            System.loadLibrary("secrets")
        }
    }

    external fun getPublicKey(context: Context): String
    external fun getSecretKey(context: Context): String
}