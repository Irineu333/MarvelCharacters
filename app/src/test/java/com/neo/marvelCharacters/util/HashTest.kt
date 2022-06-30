package com.neo.marvelCharacters.util

import junit.framework.TestCase

class HashTest : TestCase() {
    fun `test md5`() {
        assertEquals(
            "ffd275c5130566a2916217b101f26150",
            Hash.md5(1,"abcd",  "1234")
        )
    }
}