package com.heng.common_lib.singletons.coroutine_cancel_timeout
import kotlinx.coroutines.*

fun main() = runBlocking {

    val startTime = System.currentTimeMillis()

    val job = launch(Dispatchers.Default) {
        repeat(1000) {
            var i = 0
            var nextPrintTime = startTime
            while (isActive) {
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job:I am sleeping ${i++}")
                    nextPrintTime += 500L
                }
            }
        }
    }

    delay(1300L)
    println("main:I am tried fo waiting...")
    job.cancelAndJoin()
    println("main:Now I can exit...")
}