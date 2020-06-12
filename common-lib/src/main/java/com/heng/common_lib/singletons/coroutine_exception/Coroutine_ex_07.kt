package com.heng.common_lib.singletons.coroutine_exception
import kotlinx.coroutines.*

fun main() = runBlocking {

    val supervisor = SupervisorJob()

    with(CoroutineScope(coroutineContext + supervisor)) {

        val firstChild = launch(CoroutineExceptionHandler { _, _ ->  }) {
            println("111222333")
            throw AssertionError("555555555")
        }

        val secondChild = launch {
            firstChild.join()
            println("432142144321")
            try {
                delay(Long.MAX_VALUE)
            } finally {
                println("000000000")
            }
        }

        firstChild.join()
        println("supervisor cancel")
        supervisor.cancel()
        secondChild.join()
    }
}