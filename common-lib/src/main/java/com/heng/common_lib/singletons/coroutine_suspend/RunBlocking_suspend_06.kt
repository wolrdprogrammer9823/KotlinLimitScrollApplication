package com.heng.common_lib.singletons.coroutine_suspend
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.lang.ArithmeticException

fun main() = runBlocking<Unit> {

    try {
        failedConcurrentSum()
    } catch (e: ArithmeticException) {
        println("Computation failed with ArithmeticException")
    }
}

suspend fun failedConcurrentSum() : Int = coroutineScope {

    val one = async {
        try {
            delay(Long.MAX_VALUE)
            91
        } finally {
            println("The first child is called")
        }
    }

    val two = async<Int> {
        println("The second child throw an exception")
        throw ArithmeticException("")
    }

    one.await() + two.await()
}