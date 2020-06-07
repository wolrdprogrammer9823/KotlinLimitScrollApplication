package com.heng.common_lib.singletons.coroutine_cancel_timeout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val  job = launch {
        repeat(1000){i ->
            println("I am $i")
            delay(200L)
        }
    }

    delay(1300L)
    println("main:I am waiting exit")
    job.cancel()
    job.join()
    println("main:I can exit")
}