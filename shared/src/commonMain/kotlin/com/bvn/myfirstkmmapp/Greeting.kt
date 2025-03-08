package com.bvn.myfirstkmmapp

import database.NoteQueries

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}