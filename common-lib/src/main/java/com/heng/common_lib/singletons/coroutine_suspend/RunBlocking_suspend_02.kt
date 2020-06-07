package com.heng.common_lib.singletons.coroutine_suspend
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {

    val time = measureTimeMillis {
        val rsOne = async { sumOne() }
        val rsTwo = async { sumTwo() }
        println("The result is ${rsOne.await() + rsTwo.await()}")
    }

    println("The work completed in $time ms")
}

suspend fun sumOne(): Int {
    delay(500L)
    return 15
}

suspend fun sumTwo() : Int {
    delay(500L)
    return 26
}