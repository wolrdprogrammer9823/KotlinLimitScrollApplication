package com.heng.common_lib.singletons.coroutine_context_dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val request = launch {
        repeat(3){i ->
            launch {
                delay((i + 1) * 200L)
                println("coroutine $i is done")
            }
        }
        println("request:I am done and I am not explicitly join my children that are still alive")
    }

    request.join()
    println("Now processing of the request is complete")
}