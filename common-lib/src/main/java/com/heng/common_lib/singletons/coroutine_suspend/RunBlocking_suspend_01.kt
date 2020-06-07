package com.heng.common_lib.singletons.coroutine_suspend
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {

    val time = measureTimeMillis {
        val resultOne = workOne()
        val resultTwo = workTwo()
        println("The result is ${resultOne + resultTwo}")
    }

    println("The work is in $time mills completed")
}

suspend fun workOne(): Int {
    delay(1000L)
    return 13
}

suspend fun workTwo() : Int {
    delay(1000L)
    return 29
}
