package com.heng.common_lib.singletons.coroutine_exception
import kotlinx.coroutines.*
import java.lang.ArithmeticException
import java.lang.AssertionError

fun main() = runBlocking {

    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler $exception")
    }

    val job = GlobalScope.launch(handler) {
        throw AssertionError()
    }

    val work = GlobalScope.async(handler) {
        throw ArithmeticException()
    }

    joinAll(job, work)
}