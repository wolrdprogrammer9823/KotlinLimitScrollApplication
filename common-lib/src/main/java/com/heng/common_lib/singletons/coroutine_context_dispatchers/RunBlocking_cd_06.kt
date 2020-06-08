package com.heng.common_lib.singletons.coroutine_context_dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    val request = launch {

        /*全局作用域协程*/
        GlobalScope.launch {
            println("job1: I am running in GlobalScope and execute independently")
            delay(1000L)
            println("job1:I am not affected by cancellation of request")
        }

        /*子协程*/
        launch {
            delay(100L)
            println("job2:I am child of a request coroutine")
            delay(1000L)
            println("job2:I am not execute this line if my parent coroutine is canceled")
        }

    }

    delay(200L)
    request.cancel()
    delay(1000L)
    println("main:Who was survived request cancellation")
}