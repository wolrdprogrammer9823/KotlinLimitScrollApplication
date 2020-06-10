package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

@ExperimentalCoroutinesApi
fun main() = runBlocking {

    /*conflate操作符->合并一些耗时的操作*/
    val time = measureTimeMillis {
        fast().conflate().collect { value ->
            delay(300L)
            println("value $value")
        }
    }
    println("consumed time $time ms")
}

fun fast() : Flow<Int> = flow {
    for (i in 1..3) {
        delay(100L)
        emit(i)
    }
}