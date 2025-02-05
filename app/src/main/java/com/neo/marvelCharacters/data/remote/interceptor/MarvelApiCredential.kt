package com.neo.marvelCharacters.data.remote.interceptor

import com.neo.marvelCharacters.core.MarvelApi
import com.neo.marvelCharacters.util.Hash
import okhttp3.Interceptor
import okhttp3.Response

class MarvelApiCredential : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val timeStamp = System.currentTimeMillis()

        val requestUrl = chain
            .request()
            .url
            .newBuilder()
            .addQueryParameter("ts", "$timeStamp")
            .addQueryParameter("apikey", MarvelApi.publicKey)
            .addQueryParameter("hash",
                Hash.md5(
                    timeStamp,
                    MarvelApi.privateKey,
                    MarvelApi.publicKey
                )
            ).build()

        return chain.proceed(chain.request().newBuilder().url(requestUrl).build())
    }

}
