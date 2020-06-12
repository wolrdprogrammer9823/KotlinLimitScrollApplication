package com.heng.common_lib.singletons.coroutine_exception
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.IOException

fun main() = runBlocking {

    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    val job = GlobalScope.launch(handler) {
        val inner = launch {
            launch {
                launch {
                    throw IOException()
                }
            }
        }
        try {
            inner.join()
        } catch (e: Exception) {
            println("341414312414")
            throw e
        }
    }
    job.join()
}