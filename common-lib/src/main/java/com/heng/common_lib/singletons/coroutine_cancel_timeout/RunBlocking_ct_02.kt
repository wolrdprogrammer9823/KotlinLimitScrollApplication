package com.heng.common_lib.singletons.coroutine_cancel_timeout
import kotlinx.coroutines.*

fun main() = runBlocking {

    val startTime = System.currentTimeMillis()

    val job = launch(Dispatchers.Default) {
        var i = 0;
        var nextPrintTime = startTime
        while (i < 5) {
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job:I am sleeping ${i++}")
                nextPrintTime += 500L
            }
        }
    }

    delay(1300L)
    println("main:I'm tried of waiting...")
    job.cancelAndJoin()
    println("main:Now I can exit...")
}
