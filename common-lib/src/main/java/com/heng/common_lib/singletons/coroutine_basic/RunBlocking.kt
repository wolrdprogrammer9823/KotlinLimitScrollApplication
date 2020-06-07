package com.heng.common_lib.singletons.coroutine_basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    GlobalScope.launch {
        delay(1000L)
        println("World!!")
    }
    print("Hello,")
    Thread.sleep(2000L)
}