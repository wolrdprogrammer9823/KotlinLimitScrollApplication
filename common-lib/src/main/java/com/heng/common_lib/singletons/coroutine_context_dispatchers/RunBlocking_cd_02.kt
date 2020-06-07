package com.heng.common_lib.singletons.coroutine_context_dispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    launch(Dispatchers.Unconfined) {
        println("Unconfined:I am working in thread ${Thread.currentThread().name}")
        delay(500L)
        println("Unconfined:after delay in thread ${Thread.currentThread().name}")
    }

    launch {
        println("main thread:I am working in thread ${Thread.currentThread().name}")
        delay(1000L)
        println("main thread:after delay in thread ${Thread.currentThread().name}")
    }
}