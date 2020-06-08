package com.heng.common_lib.singletons.coroutine_context_dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    /*Join Context*/
    println("My job is ${coroutineContext[Job]}")
}