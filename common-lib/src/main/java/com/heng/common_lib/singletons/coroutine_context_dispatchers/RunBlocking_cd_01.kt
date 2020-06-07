package com.heng.common_lib.singletons.coroutine_context_dispatchers
import kotlinx.coroutines.*

@ObsoleteCoroutinesApi
fun main() = runBlocking<Unit> {

    launch {
        println("main thread:I am working in thread ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Unconfined) {
        println("Unconfined:I am working in thread ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Default) {
        println("Default:I am working in thread ${Thread.currentThread().name}")
    }

    launch(newSingleThreadContext("newSingleThreadContext")) {
        println("newSingleThreadContext:I am working in thread ${Thread.currentThread().name}")
    }
}
