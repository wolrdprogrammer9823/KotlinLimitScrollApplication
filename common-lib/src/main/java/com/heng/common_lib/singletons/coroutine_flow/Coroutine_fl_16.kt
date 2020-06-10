package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        doRequest().collect {value->
            delay(300L)
            println("value $value")
        }
    }
    println("consumed time $time ms")
}

fun doRequest() : Flow<Int> = flow {
    for (i in 1..3) {
        delay(100L)
        emit(i)
    }
}