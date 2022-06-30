package com.neo.marvelCharacters.security

import android.content.Context
import com.neo.marvelCharacters.util.SignatureCompat
import java.security.MessageDigest

@Suppress("UNUSED")
object SignatureUtil {
    private const val DEFAULT_ALGORITHM = "MD5"

    fun getSignature(
        context: Context,
        algorithm: String = DEFAULT_ALGORITHM
    ) = getSignatures(context, algorithm).first()

    private fun getSignatures(
        context: Context,
        algorithm: String = DEFAULT_ALGORITHM
    ) = SignatureCompat.getSignatures(context).map { signature ->
        val digested = signature.toByteArray().digest(algorithm)
        digested.joinToString(":") { "%02x".format(it).uppercase() }
    }

    private fun ByteArray.digest(
        algorithm: String = DEFAULT_ALGORITHM
    ): ByteArray = MessageDigest.getInstance(algorithm).digest(this)
}