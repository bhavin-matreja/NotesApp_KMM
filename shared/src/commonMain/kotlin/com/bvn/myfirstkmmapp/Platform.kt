package com.bvn.myfirstkmmapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform