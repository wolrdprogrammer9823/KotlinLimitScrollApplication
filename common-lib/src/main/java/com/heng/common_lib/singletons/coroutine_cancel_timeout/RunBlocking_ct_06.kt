package com.heng.common_lib.singletons.coroutine_cancel_timeout
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun main() = runBlocking {
    withTimeout(1300L) {
        repeat(1000){i ->
            println("job:I am from $i")
            delay(500L)
        }
    }
}