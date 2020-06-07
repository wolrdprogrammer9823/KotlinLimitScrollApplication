package com.heng.common_lib.singletons.coroutine_suspend

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {

    /*强烈推荐不使用这种方式  因为这种方式会有一些未知的错误*/
    val time = measureTimeMillis {

        val exOne = exFirst()
        val exTwo = exSecond()

        /*使用runBlocking来阻塞主线程，因为需要等待结果*/
        runBlocking {
            println("The result is ${exOne.await() + exTwo.await()}")
        }
    }

    println("Completed is $time ms")
}

fun exFirst() = GlobalScope.async {
    kkOne()
}

fun exSecond() = GlobalScope.async {
    kkTwo()
}

suspend fun kkOne() : Int {
    delay(1000L)
    return 19
}

suspend fun kkTwo() : Int {
    delay(1000L)
    return 27
}