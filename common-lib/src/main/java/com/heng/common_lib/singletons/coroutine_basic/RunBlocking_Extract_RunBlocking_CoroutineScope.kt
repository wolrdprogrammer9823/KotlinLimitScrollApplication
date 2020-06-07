package com.heng.common_lib.singletons.coroutine_basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        doWorld()
    }
    print("Hello,")
}

suspend fun doWorld() {
    delay(200L)
    println("World!!")
}