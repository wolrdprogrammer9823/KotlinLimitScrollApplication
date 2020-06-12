package com.heng.common_lib.singletons.coroutine_exception
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.ArithmeticException
import java.lang.RuntimeException

fun main() = runBlocking {

    val job = GlobalScope.launch {
        println("Throwing exception from launch")
        throw RuntimeException()
    }
    job.join()
    println("join failed...")

    val deferred = GlobalScope.async {
        println("Throwing exception from async")
        throw ArithmeticException()
    }
    try {
        deferred.await()
        println("unreached...")
    } catch (e: ArithmeticException) {
        println("Caught ArithmeticException")
    }
}