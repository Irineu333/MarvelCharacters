package com.neo.marvelCharacters.security

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.os.Build
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
    ) = getSignaturesCompat(context).map { signature ->
        val digested = signature.toByteArray().digest(algorithm)
        digested.joinToString(":") { "%02x".format(it).uppercase() }
    }

    private fun getSignaturesCompat(context: Context): Array<Signature> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.GET_SIGNING_CERTIFICATES
            ).signingInfo.apkContentsSigners
        } else @SuppressLint("PackageManagerGetSignatures") {
            context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.GET_SIGNATURES
            ).signatures
        }
    }

    private fun ByteArray.digest(
        algorithm: String = DEFAULT_ALGORITHM
    ): ByteArray = MessageDigest.getInstance(algorithm).digest(this)
}