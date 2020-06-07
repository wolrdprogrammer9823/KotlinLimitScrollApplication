package com.heng.common_lib.singletons.coroutine_suspend
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {

    val time = measureTimeMillis {
        println("The result is ${concurrentWork()}")
    }
    println("Completed in $time ms")
}

suspend fun concurrentWork() : Int = coroutineScope {
    val one = async {logicOne()}
    val two = async {logicTwo()}
    one.await() + two.await()
}

suspend fun logicOne():Int{
    delay(1000L)
    return 59
}

suspend fun logicTwo():Int{
    delay(1000L)
    return 43
}