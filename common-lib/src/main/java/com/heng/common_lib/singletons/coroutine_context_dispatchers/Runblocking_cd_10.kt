package com.heng.common_lib.singletons.coroutine_context_dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    val testDemo = TestDemo()
    testDemo.doSomething()
    println("Launched coroutines")
    delay(500L)
    println("destroy TestDemo")
    testDemo.destroy()
    delay(1000L)
}