package com.heng.common_lib.singletons.coroutine_suspend
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {

    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { getOne() }
        val two = async(start = CoroutineStart.LAZY) { getTwo() }
        one.start()
        two.start()
        println("The result is ${one.await() + two.await()}")
    }

    println("Completed in $time ms")
}

suspend fun getOne() : Int{
    delay(1000L)
    return 26
}

suspend fun getTwo() : Int {
    delay(1000L)
    return 18
}