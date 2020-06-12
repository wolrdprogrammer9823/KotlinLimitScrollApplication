package com.heng.common_lib.singletons.coroutine_exception
import kotlinx.coroutines.*
import java.io.IOException
import java.lang.ArithmeticException

fun main() = runBlocking {

    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception with suppressed ${exception.suppressed.contentToString()}")
    }

    val job = GlobalScope.launch(handler) {

        launch {
            try {
                delay(Long.MAX_VALUE)
            } finally {
                throw ArithmeticException() //第二个异常
            }
        }

        launch {
            delay(100L)
            throw IOException() //第一个异常
        }

        delay(Long.MAX_VALUE)
    }
    job.join()
}