package com.neo.marvelcharacters.util

import java.math.BigInteger
import java.security.MessageDigest

object Hash {
    fun md5(vararg inputs : Any): String {

        val input = inputs.joinToString("")
        val md = MessageDigest.getInstance("MD5")

        return BigInteger(1, md.digest(input.toByteArray()))
            .toString(16).padStart(32, '0')
    }
}