package com.neo.marvelCharacters

class Secrets {

    companion object {
        init {
            System.loadLibrary("secrets")
        }
    }

    external fun getPublicKey(): String
    external fun getSecretKey(): String
}