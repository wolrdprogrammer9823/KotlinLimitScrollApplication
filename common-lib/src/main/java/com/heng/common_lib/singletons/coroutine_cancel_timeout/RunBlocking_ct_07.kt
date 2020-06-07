package com.heng.common_lib.singletons.coroutine_cancel_timeout
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() = runBlocking {
    val result = withTimeoutOrNull(1300L) {
        repeat(1000){i ->
            println("job:I am from $i")
            delay(500L)
        }
        "done"
    }
    println("The result is $result")
}